(ns ktoto-service.helpers.config
  (:require
    [clojure.string :as string]
    [environ.core :refer [env]]))

(def port (Integer/parseInt (env :port "11001")))

(def db-uri
  (env :db-uri "http://localhost:5984"))

(def db-name
  (env :db-name "people"))

(def num-choices
  (env :num-choices 3))

(def num-questions
  (env :num-questions 5))

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
