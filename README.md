# Recursive Algorithms

One should consider recursive routines when using iteration instead would make the source code inelegant or hard to read. The approach is also advantageous in cases where the use of iteration would make an algorithm unnecessarily complex to implement  (Dale & Weems, 2008). Also when an algorithm guarantees that a recursion would end at some point, it makes sense to use it because you are confident that the code would not lead to a `StackOverflowError`.

The main issue that presents itself in recursion is that it can sometimes be very inefficient. When you run an algorithm using a recursion routine and then repeat the same with a classic iteration implementation, such as a `for` loop, you will realize that recursion is slower than iteration (Dale & Weems, 2008). This could be attributed to the fact that recursion causes new objects to be created many times over than what you would get with iteration.

Still, recursion wins over iteration in most cases where code readability has a high priority. Because the approach tends to involve repetitive calls to methods from within themselves, it makes it easy for a third party to grasp what is going on in the code at a glance. This is in contrast to looping where most programmers use incomprehensible variables to signal the continued operation of a loop or its termination. Take the case of the variable `i` that is ubiquitous in `for` loops. A layman would be hard pressed to explain what that variable actually means. Yet, the opposite is true in recursion. With meaningful method names, it would be obvious what the code does and what every variable means.

### Reference

​	Dale, N., & Weems, C. (2008). *Programming and problem solving with Java*. Jones and Bartlett Publishers.
