(ns ktoto-service.helpers.config
  (:require
    [clojure.string :as string]
    [environ.core :refer [env]]))

(def port (Integer/parseInt (or (env :port) "11001")))

(def couchdb-uri
  (env :db-uri "http://localhost:5984"))

(def couchdb-name
  (env :db-name "users"))

(defn all
  "List all the config variables used"
  []
  (reduce-kv
    (fn [acc k v]
      (if-not (= 'all k)
        (merge
          acc {k (var-get v)})
        acc))
    {}
    (ns-publics 'ktoto-service.helpers.config)))
