default:
	# python3 .py
	javac Strategies/Bag.java
	javac Strategies/Strategy1.java
	javac Strategies/Strategy2.java
	javac Strategies/Strategy3.java
	javac Strategies/Strategy4.java
run1:
	java Strategies/Strategy1
run2:
	java Strategies/Strategy2
run3:
	java Strategies/Strategy3
run4:
	java Strategies/Strategy4
# experiment:
# 	javac CompareTasks.java
# 	java CompareTasks

clean:
	rm -rf input_1000.txt
	rm -rf input_10000.txt
	rm -rf input_25000.txt
	rm -rf input_50000.txt
	rm -rf input_99999.txt
	rm -rf Task1.class
	rm -rf Task2.class
	rm -rf Task3.class
	rm -rf Task4.class
	rm -rf Strategy1Comparator.class
	rm -rf Strategy2Comparator.class
	rm -rf Strategy3Comparator.class
	rm -rf Strategy4Comparator.class
	rm -rf Bag.class
