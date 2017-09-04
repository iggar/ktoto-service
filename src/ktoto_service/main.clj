(ns ktoto-service.main
  (:require
    [org.httpkit.server :refer [run-server]]
    [ktoto-service.handler :as handler]
    [ktoto-service.helpers.config :as config])
  (:gen-class))

(defn -main []
  (print "Server starting on port " config/port)
  (run-server handler/app {:port config/port}))
