(ns mametipsum.db)

(defn parse [file-name]
  (with-open [fd (clojure.java.io/reader file-name)]
    (let [lines (line-seq fd)]
      (zipmap lines (map count lines)))))

(defn remove-directories [list]
  (remove #(.isDirectory %) list))

(defn init-db []
  (println
   (for [file-name (remove-directories (file-seq (clojure.java.io/file "db")))] (parse file-name))))
