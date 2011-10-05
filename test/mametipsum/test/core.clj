(ns mametipsum.test.core
  (:use [clojure.test])
  (:use [mametipsum.db]))

(deftest test-mametipsum-db
  (let [script (init-db)]
    (is (= (count (first script)) 1331))))
