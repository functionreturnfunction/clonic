(ns clonic.test.core
  (:use [clonic.core] :reload-all)
  (:use [clojure.test])
  (:use clojure.string)
  (:use clj-time.core))

;(run-tests 'clonic.test.core)

(defn date-eq? [date1 date2]
 (and  (= (day date1) (day date2)) 
       (= (month date1) (month date2)) 
       (= (hour date1) (hour date2)) 
       (= (year date1) (year date2)) ))

(defn deq? [date exp]  (is (date-eq? date (parse-date exp))) )

(def current (now))
(def current-year (year current))

(defmacro expect [ expectation date ]
  `(deftest ~(symbol (str "test-"  (replace " " "-" expectation)))   (deq? ~date ~expectation))  )

(deftest test-now
  (deq? current "now"))

(deftest test-tomorrow
  (deq? (plus current (days 1)) "tomorrow"))

(deftest test-MMM-dd-yyyy
  (date-time current-year 5 27)
  (format "may 27 %d" current-year))

(deftest test-MMM-dd-yy-ht
  (date-time current-year 5 28 17)
  (format "may 28 %d 5pm" current-year))

(deftest test-MMM-dd-yy-ht2
  (date-time current-year 5 28 5)
  (format "may 28 %d 5am" current-year))
