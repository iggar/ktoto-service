(ns ktoto-service.database
  (:require
    [ktoto-service.helpers.config :as config]
    [clojure.data.json :as json]
    [ktoto-service.helpers.config :as config]
    [com.ashafa.clutch :as clutch]
    [environ.core :refer [env]]))

(def users-db-uri
  (str config/couchdb-uri "/" config/couchdb-name))

(defn fetch-user [user-id]
  (clutch/get-document users-db-uri user-id))

(defn fetch-game [game-id]
  (json/read-str (slurp "resources/game_sample2.json") :key-fn keyword))

(defn save-game []
  nil)
