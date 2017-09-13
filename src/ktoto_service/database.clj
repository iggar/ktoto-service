(ns ktoto-service.database
  (:require
    [ktoto-service.helpers.config :as config]
    [clojure.data.json :as json]
    [ktoto-service.helpers.config :as config]
    [com.ashafa.clutch :as clutch]
    [environ.core :refer [env]]))

(def db-uri
  (str config/db-uri "/" config/db-name))

(defn create-database!
  "Creates database if it's not available yet"
  []
  (clutch/get-database config/db-name))

(defn fetch-user [user-id]
  (clutch/get-document db-uri user-id))

(defn fetch-game [game-id]
  (json/read-str (slurp "resources/game_sample2.json") :key-fn keyword))

(defn save-game [game]
  (:_id (clutch/put-document db-uri {:question game :datetimestamp (java.util.Date.)})))
