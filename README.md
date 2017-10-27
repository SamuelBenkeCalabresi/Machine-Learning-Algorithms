# Machine Learning Algorithms

Implementation with pure Java of common Machine Learning algorithms and Artificial Neural Networks (ANNs).

## Abstract

The implementation of the learning algorithms uses as dataset for training and testing the Optical Recognition of Handwritten Digits Dataset from the repository of the University of California at Irvine (UCI dataset). 
Part of the project focuses on this dataset, but all the machine learnings models are implemented to be used for any dataset (depending if supervised/unsupervised) and a dataset package contains the data structures to abstract the dataset from the files, LabelledData and BinaryData respectively. Multiple machine learning algorithms create a little framework that can be used for supervised and unsupervised tasks.
There is a specific package for any machine learning model, anyone containing the code to train the classifier and run a two fold test. The classes named Test + name of the model contain the main method to run the program.  A util class named TwoFoldTest is inside such packages implementing the logic to print in the console the accuracy of the tests. 
On the test folder there are packages containing the main classes with the main method to run the program. 

## Prerequisites

MNIST database and the Optical Recognition of Handwritten Digits dataset from the repository of the University of California at Irvine (UCI dataset). They are contained in the project, but can be found as follows:
[MNIST](http://yann.lecun.com/exdb/mnist/) and
[UCI](http://archive.ics.uci.edu/ml/datasets/optical+recognition+of+handwritten+digits).

## License

This project is licensed under the Apache License - see the [LICENSE.md](LICENSE.md) file for details
