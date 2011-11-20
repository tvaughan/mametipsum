(ns mametipsum.test.core
  (:require
   [mametipsum.db :as db])
  (:use
   [clojure.test]))

(defn db-fixture [f]
  (db/init)
  (f))

(use-fixtures :once db-fixture)

(deftest db-testsuite
  (let [scripts (db/list-scripts)]
    (is (= ((first scripts) :title) "Glengarry Glen Ross"))))
