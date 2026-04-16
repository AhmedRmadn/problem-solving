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

</details>