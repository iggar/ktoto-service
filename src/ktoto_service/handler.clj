(ns ktoto-service.handler
  (:require [compojure.api.sweet :refer :all]
            [ring.util.http-response :refer :all]
            [ktoto-service.database :as database]
            [ktoto-service.helpers.config :as config]
            [schema.core :as s]))

(def app
  (api
    (GET "/internal/healthcheck" []
      (ok {:status "This is fine"}))
    (GET "/internal/config" []
      (ok (config/all)))
    (GET "/invernal/games" []
      (ok (config/all)))
    (POST "/game/new" []
      (ok {:status "New game created"}))
    (GET "/game" []
      :query-params [game-id :- String]
      (if-not (empty? game-id)
        (do
          (print "Fetching game: " game-id)
          (ok
            (database/fetch-game game-id)))
        (bad-request)))))
