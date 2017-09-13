(ns ktoto-service.game
  (:require [clojure.data.json :as json]
            [ktoto-service.helpers.config :as config]
            [ktoto-service.database :as db]))

(defn users
  "Fetch all users into map"
  []
  (json/read-str (slurp "resources/users_sample_photo.json") :key-fn keyword))

;         questions-without-photo (map #(dissoc % :photo) questions)]

(defn choices
  "Produces a quiz question with 'nchoices' choices using
  data from 'usercol' map"
  [nchoices userscol]
  (let [rand-choices (take nchoices (shuffle userscol))
        answer (:photo (nth rand-choices (rand-int nchoices)))]
  (hash-map :choices rand-choices :answer answer)))

(defn save-game!
  [game-db]
  (db/save-game game-db))

(defn new-game
  "Returns a new game with number 'nquestions' of questions,
  given a collection of users 'usercol'"
  [nquestions userscol]
  (let [game-db (loop [col [] n 0]
                  (if (< n nquestions)
                    (recur (conj col (choices
                                      (Integer. config/num-choices)
                                      userscol)) (inc n))
                    col))]
    {:questions (map #(dissoc % :answer) game-db) :id (save-game! game-db)}))

(defn play
  "Checks whether the player answer is correct for the given question"
  [question-id choice]
  (let [game (db/fetch-game nil)
        correct-answer (:answer (nth game question-id))
        user-answer (:photo (nth (:choices (nth game question-id)) choice))]
    (= correct-answer user-answer)))
