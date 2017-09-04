(ns ktoto-service.database
  (:require
    [ktoto-service.helpers.config :as config]
    [clojure.data.json :as json]))

(def dummy-game
  (json/write-str
    {
      :id "00001",
      :choice-set [{
              :choice-id  "001"
              :choices ["Mary","Patricia","Linda"]
              :correct-answer "Mary"
        },
        {
          :choice-id "002"
          :choices ["Barbara","Elizabeth","Jennifer"]
          :correct-answer "Elizabeth"
        },
        {
          :choice-id "003",
          :choices ["Margaret","Dorothy","Lisa"]
          :correct-answer "Lisa"
        }]}))

(defn fetch-game [game-id]
  dummy-game)
