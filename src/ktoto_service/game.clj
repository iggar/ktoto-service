(ns ktoto-service.game
  (:require [clojure.data.json :as json]
            [ktoto-service.helpers.config :as config]
            [ktoto-service.database :as database]))

(defn users
  "Fetch all users into map"
  []
  (json/read-str (slurp "resources/users_sample_photo.json") :key-fn keyword))

(defn question-old
  "Produces a quiz question with 'nchoices' choices using
  data from 'usercol' map"
  [nchoices userscol]
  (let [questions (take nchoices (shuffle userscol))
        answer (:photo (nth questions (rand-int nchoices)))
        questions-without-photo (map #(dissoc % :photo) questions)]
  (vector questions-without-photo answer)))

(defn question
  "Produces a quiz question with 'nchoices' choices using
  data from 'usercol' map"
  [nchoices userscol]
  (let [questions (take nchoices (shuffle userscol))
        answer (:photo (nth questions (rand-int nchoices)))]
  (vector questions answer)))

(defn new-game
  "Returns a new game with number 'nquestions' of questions,
  given a collection of users 'usercol'"
  [nquestions userscol]
  (loop [col [] n 0]
    (if (< n nquestions)
      (recur (conj col (question (Integer. config/num-choices) userscol)) (inc n))
      col)))

(defn play
  "Checks whether the player answer is correct for the given question"
  [question-id choice]
  (let [game (database/fetch-game nil)
        correct-answer (:photo (nth (first (nth game question-id)) choice))
        user-answer (second (nth game question-id))]
    (= correct-answer user-answer)))
