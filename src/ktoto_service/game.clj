(ns ktoto-service.game
  (:require [clojure.data.json :as json]
            [ktoto-service.helpers.config :as config]))

(defn users
  "Fetch all users into map"
  []
  (json/read-str (slurp "resources/users_sample_photo.json") :key-fn keyword))

(defn question
  "Produces a quiz question with 'nchoices' choices using
  data from 'usercol' map"
  [nchoices userscol]
  (let [questions (take nchoices (shuffle userscol))
        answer (:photo (nth questions (rand-int nchoices)))
        questions-without-photo (map #(dissoc % :photo) questions)]
  (vector questions-without-photo answer)))

(defn new-game
  "Returns a new game with number 'nquestions' of questions,
  given a collection of users 'usercol'"
  [nquestions userscol]
  (loop [col [] n 0]
    (if (< n nquestions)
      (recur (conj col (question (Integer. config/number-choices) userscol)) (inc n))
      col)))
