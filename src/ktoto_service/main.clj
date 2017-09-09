(ns ktoto-service.main
  (:require
    [org.httpkit.server :refer [run-server]]
    [ktoto-service.handler :as handler]
    [ktoto-service.helpers.config :as config]
    [ktoto-service.database :as database])
  (:gen-class))

(defn -main []
  (print "Server starting on port " config/port)
  (database/create-database!)
  (run-server handler/app {:port config/port}))
