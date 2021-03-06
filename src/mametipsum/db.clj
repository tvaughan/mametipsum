(ns mametipsum.db
  (:require [mametipsum.scripts :as scripts]
            [clojure.java.io :refer [resource]]
            [clojure.string :refer [split]]))

(defn- remove-directories [list]
  (remove #(.isDirectory %) list))

(defn- count-words [string]
  (count (split string #" ")))

(defn- read-blobs []
  (remove-directories (file-seq (clojure.java.io/file (resource "db")))))

(defstruct line :string :nwords)

(defn- parse-blob [strings]
  (doall
   (map (fn [string nwords]
          (struct line string nwords))
        strings (map count-words strings))))

(defn- create-blob [filename]
  (with-open [fd (clojure.java.io/reader filename)]
    (parse-blob (line-seq fd))))

(defn init []
  (let [handle (scripts/get-handle)]
    (doseq [blob (read-blobs)]
      (scripts/create-script handle (.getName blob) (create-blob blob)))))
