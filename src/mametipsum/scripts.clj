(ns mametipsum.scripts)

(def *scripts* (ref {}))

(defn get-handle []
  *scripts*)

(defn list-titles [scripts]
  (keys @scripts))

(defn- print-titles [scripts]
  (doseq [title (list-titles scripts)]
    (println title)))

(defn list-lines [scripts]
  (vals @scripts))

(defn- print-lines [scripts]
  (doseq [lines (list-lines scripts)]
    (println lines)))

(defn create-script [scripts title data]
  (dosync (alter scripts assoc title data)))

(defn- iter-script [script nwords]
  (let [script (nthnext (cycle script) (rand-int (count script)))]
    (loop [i 0 current (first script) remainder (rest script) lines []]
      (if (< i nwords)
        (let [total (+ i (current :nwords))]
          (recur total (first remainder) (rest remainder) (conj lines (current :string))))
        (apply str (interpose " " lines))))))

(defn read-script [scripts title nblocks nwords]
  (loop [i 0 script (scripts title) blocks []]
    (if (< i nblocks)
      (let [total (inc i)]
        (recur total script (cons (iter-script script nwords) blocks)))
      blocks)))

(defn update-script [scripts title data]
  ;; TODO
  )

(defn delete-script [scripts title]
  (dosync (alter scripts dissoc title)))
