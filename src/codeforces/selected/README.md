# 📘 My Algorithm Playbook
A personal database of competitive programming "Aha!" moments, core tricks, and algorithmic templates.

## 🗂️ Table of Contents
* [Math & Number Theory](#math--number-theory)
* [Greedy & Constructive](#greedy--constructive)
* [Dynamic Programming](#dynamic-programming)

---

## 🧮 Math & Number Theory

<details>
<summary><b>1. Removal of a Sequence (Hard Version)</b> | <code>Div 2 D</code> | <code>10^12 Math Simulation</code></summary>

> **Link:** [Insert Codeforces Link]
> **Tags:** `math`, `implementation`, `harmonic lemma`

### 💡 The "Aha!" Moment
Don't simulate the array shrinking forward. Track the final index backward through time! Because $X = 10^{12}$, calculate exactly how many operations it takes for your division "speed" to increase, and teleport forward in $O(\sqrt{X})$ time.

### 🪤 The Trap (What failed)
A standard $O(X)$ `while` loop gets a Time Limit Exceeded (TLE) because $\lfloor (K-1)/(Y-1) \rfloor$ stays exactly the same for billions of operations.

### 🛠️ The Strategy
1. **Find Speed:** $Q = \lfloor (K - 1) / (Y - 1) \rfloor$. 
2. **Find Room Left:** $R = (K - 1) \pmod{Y - 1}$. The room left before $Q$ increases is $(Y - 1) - 1 - R$.
3. **Calculate Steps:** `steps = (room / Q) + 1`.
4. **Teleport:** Add `(steps * Q)` to $K$, subtract `steps` from $X$.

### ⏱️ Complexity
* **Time:** $O(\sqrt{X})$ 
* **Space:** $O(1)$

### 💻 Core Logic Snippet
```java
long W = y - 1;
while (x > 0 && k <= 1000000000000L) {
    long Q = (k - 1) / W;
    if (Q == 0) break; 
    
    long R = (k - 1) % W;           
    long room = W - 1 - R;          
    long steps = (room / Q) + 1;    
    
    steps = Math.min(steps, x);
    k += steps * Q;
    x -= steps;
}
```
</details>

## 🧮 Graph & Trees

<details>
<summary><b>E. Idiot First Search</b> | <code>Codeforces Round 1080 (Div. 3)</code> | </summary>

> **Link:** https://codeforces.com/problemset/problem/2195/E

> **Tags:** `trees`, `dfs and similar`, `dp`

### 🛠️ The Strategy
1. Assume vertex $U$ has left $L$, right $R$, and parent $P$.
2. Assume array `depth` where `depth[U]` is the total seconds to explore down to the leaves without going to the parent.
3. `depth[U] = 4 + depth[L] + depth[R]` (4 seconds for the state transitions: $U \to L \to U \to R \to U$). If $X$ is a leaf, `depth[X] = 0`.
4. Assume array `total` where `total[U]` is the total seconds to reach the root.
5. `total[U] = depth[U] + total[P] + 1` (1 second for the step $U \to P$).
  

### ⏱️ Complexity
* **Time:** $O(V + E)$ 
* **Space:** $O(V)$

### 💻 java file 
> **Link:** src/codeforces/selected/IdiotFirstSearch.java
</details>

## 🧮 Math & Number Theory

<details>
<summary><b>1. Removal of a Sequence (Hard Version)</b> | <code>Div 2 D</code> | <code>10^12 Math Simulation</code></summary>

> **Link:** [Insert Codeforces Link]
> **Tags:** `math`, `implementation`, `harmonic lemma`

### 💡 The "Aha!" Moment
Don't simulate the array shrinking forward. Track the final index backward through time! Because $X = 10^{12}$, calculate exactly how many operations it takes for your division "speed" to increase, and teleport forward in $O(\sqrt{X})$ time.

### 🪤 The Trap (What failed)
A standard $O(X)$ `while` loop gets a Time Limit Exceeded (TLE) because $\lfloor (K-1)/(Y-1) \rfloor$ stays exactly the same for billions of operations.

### 🛠️ The Strategy
1. **Find Speed:** $Q = \lfloor (K - 1) / (Y - 1) \rfloor$. 
2. **Find Room Left:** $R = (K - 1) \pmod{Y - 1}$. The room left before $Q$ increases is $(Y - 1) - 1 - R$.
3. **Calculate Steps:** `steps = (room / Q) + 1`.
4. **Teleport:** Add `(steps * Q)` to $K$, subtract `steps` from $X$.

### ⏱️ Complexity
* **Time:** $O(\sqrt{X})$ 
* **Space:** $O(1)$

### 💻 Core Logic Snippet
```java
long W = y - 1;
while (x > 0 && k <= 1000000000000L) {
    long Q = (k - 1) / W;
    if (Q == 0) break; 
    
    long R = (k - 1) % W;           
    long room = W - 1 - R;          
    long steps = (room / Q) + 1;    
    
    steps = Math.min(steps, x);
    k += steps * Q;
    x -= steps;
}
```
</details>

## 🧮 Graph & Trees

<details>
<summary><b>B1. Sub-RBS (Easy Version)</b> | <code>Codeforces Round 1073 (Div. 1)</code> | <code>Greedy / Bracket Balance</code></summary>

> **Link:** [Codeforces Problem](https://codeforces.com/problemset/problem/2190/B1)
> **Source Code:** [SubRBSEasyVersion.java](src/codeforces/selected/SubRBSEasyVersion.java)
> **Tags:** `combinatorics`, `constructive algorithms`, `greedy`, `strings`, `two pointers`

### 💡 The "Aha!" Moment
To make a subsequence $t$ "better" than $s$, the simplest way is to find the first spot where $t$ can have a `(` while $s$ has a `)`. 
We can force this to happen by finding a `)(` pattern in the string and **deleting the `)`**. This causes the `(` to slide one position to the left, instantly making $t$ lexicographically better! Because we drop exactly one `)` and must balance it by dropping exactly one `(`, the maximum possible length of a valid solution is always exactly **$n - 2$**.

### 🪤 The Trap (What failed)
You can't just delete *any* `)` in a `)(` pair. If you delete a `)`, you break the Regular Bracket Sequence (RBS). To fix it, you must delete an open `(` somewhere further down the string. If there aren't enough remaining closing brackets to cover the currently open ones, deleting a `(` will make the rest of the string invalid. 

### 🛠️ The Strategy
1. Keep a running `count` of the prefix balance (add 1 for `(`, subtract 1 for `)`).
2. Scan the string looking for the adjacent pair `)(` at indices `i` and `i+1`.
3. If we find `)(`, we simulate deleting the `)`. The balance at this exact moment would be `count + 1` (since we kept the next `(` but dropped the `)`).
4. **Validity Check:** We check if the number of currently open brackets (`count + 1`) is strictly less than the number of remaining characters in the string (`n - i - 2`). If true, we have enough room to close the brackets and safely delete a `(`.
5. The moment we find a valid `)(` pair, we stop and print $n - 2$. If the loop finishes without finding one, print `-1`.

### ⏱️ Complexity
* **Time:** $O(N)$ (A single pass through the string)
* **Space:** $O(1)$ (Only storing a few primitive variables)

</details>
