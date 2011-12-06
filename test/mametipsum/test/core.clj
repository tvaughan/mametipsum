(ns mametipsum.test.core
  (:require
   [mametipsum.db :as db])
  (:use
   [clojure.test]))

(defn db-fixture [f]
  (db/init)
  (f))

(use-fixtures :once db-fixture)

(defn- in?
  [seq elm]
  (some #(= elm %) seq))

(deftest db-testsuite
  (let [titles (db/get-script-titles (db/get-scripts))]
    (is (true? (in? titles "Glengarry Glen Ross")))
    (is (true? (in? titles "Speed the Plow")))))
