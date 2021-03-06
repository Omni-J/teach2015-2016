#include <iostream>
using namespace std;

class StackError{};

template<typename T>
class Stack {
	const static int STACK_SIZE = 10;
	int top_;
	T data_[STACK_SIZE];
public:
	Stack()
	: top_(0)
	{}
	
	bool empty() const {
		return top_ == 0;
	}

	bool full() const {
		return top_ == STACK_SIZE;
	}
	
	void push(T val) {
		if(full()) {
			throw StackError();
		}
		data_[top_++] = val;
	}
	
	T pop() {
		if(empty()) {
			throw StackError();
		}
		return data_[--top_];
	}
	
	void foo() {
		T val;
		val.get_x();
	}
	
};


class Foo {

public:
	int get_x() {
		return 42;
	}
};

int main() {
	Stack<int> ist;
	Stack<double> dst;

	// ist.foo();
	// dst.foo();
	
	Stack<Foo> fst;
	fst.foo();
	
	
	for(int i =0 ;i<10; ++i) {
		ist.push(i*10);
		dst.push(i*0.01);
	}

	while(!ist.empty()) {
		cout << ist.pop() << ' ';
	}
	cout << endl;
	
	while(!dst.empty()) {
		cout << dst.pop() << ' ';
	}
	cout << endl;
	
	
	return 0;
}

