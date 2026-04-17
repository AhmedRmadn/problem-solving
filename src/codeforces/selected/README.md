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

<details>
<summary><b>D1. Tree Coloring (Easy Version)</b> | <code>Hello 2026</code> | <code>Tree BFS & Greedy Bounds</code></summary>

> **Link:** [Codeforces Problem](https://codeforces.com/problemset/problem/2183/D1)
> **Source Code:** [TreeColoringEasyVersion.java](src/codeforces/selected/TreeColoringEasyVersion.java)
> **Tags:** `constructive algorithms`, `dfs and similar`, `greedy`, `trees`

### 💡 The "Aha!" Moment
When finding the minimum number of colors (operations) subject to strict constraints, look for the unavoidable bottlenecks.
1. **The Level Constraint:** No two nodes at the same depth can share an operation. So, if the widest level has $W$ nodes, you mathematically *must* use at least $W$ operations.
2. **The Edge Constraint:** No parent can share an operation with its child. Since all children of a node $U$ are on the exact same depth, no two children can share an operation either. This means a parent and **all** of its children must have mutually distinct colors! This requires $1 + \text{children}(U)$ operations.
Because this is a tree, these two local minimums are actually sufficient. The global answer is just the maximum of these bottlenecks!

### 🪤 The Trap (What failed)
A common mistake when calculating the "parent + children" bottleneck is just taking the maximum degree of any node. For an internal node, its degree in an undirected tree is exactly its children + 1 (the parent). But for the **root** node, its degree is *only* its children. If you just take `max(degree)`, you will undercount the root by 1. You must manually add `+1` to the root's adjacency list size!

### 🛠️ The Strategy
1. Build the tree using an adjacency list.
2. Run a standard Breadth-First Search (BFS) starting from the root (Node 0) to process the tree level by level.
3. Track two things during the BFS:
   * `currSize` (or `q.size()`): The width of the current level. Update the global `max` with this.
   * The "family size" of the current node: `tree[u].size()`. If it's the root, treat it as `size + 1`. Update the global `max` with this.
4. Return `max`.

### ⏱️ Complexity
* **Time:** $O(V + E)$ (A standard BFS completely traversing the tree)
* **Space:** $O(V)$ (Storing the adjacency list, the `visited` array, and the BFS `Queue`)

</details>


## 🧮 Greedy 

<details>
<summary><b>B1. Sub-RBS (Easy Version)</b> | <code>Codeforces Round 1073 (Div. 1)</code> | <code>Greedy / Bracket Balance</code></summary>

> **Link:** [Codeforces Problem](https://codeforces.com/problemset/problem/2190/B1)
> **Source Code:** [SubRBSEasyVersion.java](https://github.com/AhmedRmadn/problem-solving/blob/master/src/codeforces/selected/SubRBSEasyVersion.java)
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

<details>
<summary><b>C. War Strategy</b> | <code>Hello 2026</code> | <code>Math & Greedy</code></summary>

> **Link:** [Codeforces Problem](https://codeforces.com/problemset/problem/2183/C)
> **Source Code:** [WarStrategy.java]([src/codeforces/selected/WarStrategy.java](https://github.com/AhmedRmadn/problem-solving/blob/master/src/codeforces/selected/WarStrategy.java))
> **Tags:** `binary search`, `greedy`, `math`, `two pointers`

### 💡 The "Aha!" Moment
To maximize our fortified bases, we should pick one "primary" side to expand as far as possible, say distance $d$. 
To reach distance $d$, we need $d$ soldiers. Since we start with 1, we must wait $d-1$ days for the rest to spawn, plus $d$ days to physically move the pipeline outward. Total cost: **$2d - 1$ days**.

Here is the trick: because a new soldier spawns *every single day*, waiting $2d-1$ days creates a massive surplus of soldiers piled up at home base $k$. To fortify the *other* side, we no longer need to wait for spawns! We just take that giant stack at $k$ and slide it over step-by-step. Sliding a stack to cover distance $x$ takes exactly $x$ days.

### 🪤 The Trap (What failed)
1. **Miscalculating the secondary side:** Assuming the second side also takes $2x - 1$ days to fortify. It only takes $x$ days because the soldiers are already pre-spawned and waiting at $k$!
2. **Missing the $d$ upper bound:** The secondary side expansion can never exceed $d$. Why? Because if we expanded further on the secondary side, *that* side would actually be our primary side. You must cap the secondary distance using `Math.min(..., d)`.

### 🛠️ The Strategy
1. Loop through every base `curr` from $1$ to $n$, treating the distance from $k$ to `curr` as our "primary" expansion distance $d = |k - curr|$.
2. Calculate `numDays = 2 * d - 1`. If `numDays > m`, we don't have enough time to reach this base. Skip it.
3. Calculate the available space on the opposite side: `otherSide = (curr > k) ? k - 1 : n - k`.
4. Calculate how far we can push our surplus stack into the `otherSide`. This is limited by three strict bounds:
   * The days we have left: `m - numDays`
   * The physical edge of the map: `otherSide`
   * The symmetry rule (can't exceed primary): `d`
5. The total fortified bases for this iteration is `d` (primary) + `1` (home base) + `dFromOtherSide`. Keep a running maximum.

### ⏱️ Complexity
* **Time:** $O(N)$ (A single loop checking each base as the primary target)
* **Space:** $O(1)$ (Only storing a few primitive variables)

</details>

## 🧮 Two Pointers 

<details>
<summary><b>E. The Robotic Rush</b> | <code>Codeforces Round 1074 (Div. 4)</code> | <code>Two Pointers & Priority Queues</code></summary>

> **Link:** [Codeforces Problem](https://codeforces.com/problemset/problem/2185/E)
> **Source Code:** [TheRoboticRush.java](https://github.com/AhmedRmadn/problem-solving/blob/master/src/codeforces/selected/TheRoboticRush.java)
> **Tags:** `binary search`, `greedy`, `implementation`, `two pointers`, `data structures`

### 💡 The "Aha!" Moment
Instead of simulating every robot's position step-by-step (which is too slow), we shift our perspective to the **global net displacement**. 
Because all robots move together, we can calculate exactly how far a robot needs to travel left or right to hit its nearest spike. We put these "fatal distances" into two Min-Heaps (one for the left, one for the right). As the global offset shifts left and right, we just check the top of the heaps to see if the net displacement has crossed anyone's fatal threshold!

### 🪤 The Trap (What failed)
1. **Double Counting Deaths:** A robot could hit its left spike and die. Later, the instructions might swing the global offset far to the right, crossing that dead robot's right fatal threshold. If you aren't careful, the `rightQ` will "kill" it a second time, corrupting your `currentAlive` counter. You **must** use an `alive[]` boolean array.
2. **Absolute vs. Net Distance:** `leftMove` and `rightMove` alone aren't enough. The distance to the left spike is consumed by the *net* shift left (`leftMove - rightMove`). 

### 🛠️ The Strategy
1. **Sort & Two Pointers:** Sort the robots ($A$) and the spikes ($B$). For each robot, use a pointer to find the closest spike to its left and the closest spike to its right. Store these distances in `leftSpike[]` and `rightSpike[]`.
2. **Build the Heaps:** Push all robot indices into `leftQ` (ordered by `leftSpike` distance) and `rightQ` (ordered by `rightSpike` distance).
3. **Simulate the Offset:** Read the instructions one by one, keeping a running total of `leftMove` and `rightMove`.
4. **Purge the Dead:** - Check `leftQ`: While the top robot's `leftSpike` distance $\le$ `leftMove - rightMove`, pop it. If it is still `alive`, mark it dead and decrease `currentAlive`.
   - Check `rightQ`: While the top robot's `rightSpike` distance $\le$ `rightMove - leftMove`, pop it. If it is still `alive`, mark it dead and decrease `currentAlive`.
5. Print `currentAlive` after every step.

### ⏱️ Complexity
* **Time:** $O(N \log N + M \log M + K \log N)$ (Sorting the arrays takes $N \log N$ and $M \log M$. Polling from the PQs during the $K$ instructions takes at most $N \log N$ overall since each robot is popped at most twice).
* **Space:** $O(N + M)$ (To store the distances, arrays, and queues).

</details>
