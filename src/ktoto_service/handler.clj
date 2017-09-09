(ns ktoto-service.handler
  (:require [compojure.api.sweet :refer :all]
            [ring.util.http-response :refer :all]
            [ktoto-service.database :as database]
            [ktoto-service.helpers.config :as config]
            [schema.core :as s]
            [clojure.data.json :as json]))


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

(def app
  (api
    (GET "/internal/healthcheck" []
      (ok {:status "This is fine"}))
    (GET "/internal/config" []
      (ok (config/all)))
    (GET "/invernal/games" []
      (ok (config/all)))
    (POST "/game/new" []
      (ok (new-game 3 (users))))
    (GET "/user" []
      :query-params [user-id :- String]
      (if-not (empty? user-id)
        (do
          (print "Fetching user: " user-id)
          (ok
            (database/fetch-user user-id)))
        (bad-request)))
    (GET "/game" []
      :query-params [game-id :- String]
      (if-not (empty? game-id)
        (do
          (print "Fetching game: " game-id)
          (ok
            (database/fetch-game game-id)))
        (bad-request)))))
