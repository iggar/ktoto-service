(ns ktoto-service.main
  (:require
    [org.httpkit.server :refer [run-server]]
    [ktoto-service.handler :as handler]
    [ktoto-service.helpers.config :as config]
    [ktoto-service.database :as database]
    [clojure.tools.logging :as log])
  (:gen-class))

(defn -main []
  (log/info "Server starting on port " config/port)
  (try
    (database/create-database!)
    (catch Exception e (log/info (str "Exception: " (.getMessage e)))))
  (run-server handler/app {:port config/port}))
