(ns ktoto-service.handler
  (:require [compojure.api.sweet :refer :all]
            [ring.util.http-response :refer :all]
            [ktoto-service.database :as database]
            [ktoto-service.helpers.config :as config]
            [ktoto-service.game :as game]
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
      (ok (game/new-game config/number-choices (game/users))))
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
