# 📘 My Algorithm Playbook
A personal database of competitive programming "Aha!" moments, core tricks, and algorithmic templates.

## 🗂️ Table of Contents
* [Math & Number Theory](#math--number-theory)
* [Greedy & Constructive](#greedy--constructive)
* [Dynamic Programming](#dynamic-programming)

---

## 🧮 Math & Number Theory

<details>
<summary><b>C. Kanade's Perfect Multiples</b> | <code>Codeforces Round 1068 (Div. 2)</code> | <code>Number Theory & Greedy</code></summary>

> **Link:** [Codeforces Problem](https://codeforces.com/problemset/problem/2173/C)
> **Source Code:** [KanadePerfectMultiples.java](https://github.com/AhmedRmadn/problem-solving/blob/master/src/codeforces/selected/KanadePerfectMultiples.java)
> **Tags:** `brute force`, `constructive algorithms`, `greedy`, `number theory`

### 💡 The "Aha!" Moment
How do we confidently build the set $B$? Start from the absolute smallest unvisited number $x$ in array $A$. 
Rule 1 states $x$ must have a divisor in $B$. Could we pick a divisor $d < x$ and put it in $B$? **No.** Rule 2 states that if $d$ is in $B$, all its multiples (including $d$ itself) must be in $A$. Because we process elements in ascending order, if a valid $d$ existed in $A$, we would have processed it earlier, and it would have already "covered" $x$ as one of its multiples! 
Since $x$ is still unvisited, no such divisor exists. Therefore, our hand is forced: **we mathematically must add $x$ itself to $B$.**

### 🪤 The Trap (What failed)
1. **The $10^9$ Memory Trap:** $k$ can be up to $10^9$. If you try to use a boolean array of size $k$ to track visited states, you will get an instant Memory Limit Exceeded (MLE). You *must* use a `HashMap` or `HashSet` which bounds the space to $O(N)$.
2. **The $10^9$ Time Limit Trap:** If $x=1$ and $k=10^9$, checking all multiples up to $k$ requires $10^9$ iterations. You **must** immediately `break` the loop the second a multiple is not found in the map. Because there are only $N$ elements in the array, the `break` ensures the inner loop rarely executes, keeping the time complexity strictly bound by $O(N \log N)$.

### 🛠️ The Strategy
1. Load all elements of $A$ into a Min-Heap (`PriorityQueue`) so we can process them from smallest to largest.
2. Store all elements in a `HashMap<Long, Boolean>` to allow $O(1)$ lookups and track whether an element has been "covered" (visited).
3. Pop the smallest element $x$. If it is already marked `true` (covered by a previous element's multiples), skip it.
4. If $x$ is unvisited, add $x$ to our answer set $B$.
5. Iterate through all multiples of $x$ ($x, 2x, 3x \dots$) up to $k$:
   * If a multiple is **missing** from the map, it violates Rule 2. The configuration is impossible. Set a flag to `false` and break completely.
   * If the multiple **exists**, mark it as `true` (covered) in the map.
6. If the flag is false, output `-1`. Otherwise, print the size of $B$ followed by its elements.

### ⏱️ Complexity
* **Time:** $O(N \log N)$ (Sorting via the Priority Queue takes $N \log N$. Because of the early `break` condition, the harmonic series of checking multiples is bounded by the $N$ elements actually present in the map).
* **Space:** $O(N)$ (To store the elements in the HashMap, HashSet, and Priority Queue).

</details>

<details>
<summary><b>C2. Renako Amaori and XOR Game (Hard)</b> | <code>Codeforces Round 1065</code> | <code>Game Theory & Bitmasks</code></summary>

> **Link:** [Codeforces Problem](https://codeforces.com/problemset/problem/2171/C2)
> **Source Code:** [RenakoAmaoriXORGameHardVersion.java](https://github.com/AhmedRmadn/problem-solving/blob/master/src/codeforces/selected/RenakoAmaoriXORGameHardVersion.java)
> **Tags:** `bitmasks`, `games`, `greedy`

### 💡 The "Aha!" Moment
Let $A$ be Ajisai's final XOR score, and $B$ be Mai's. The global XOR sum $S = A \oplus B$ is completely immune to swaps! Because we are only swapping $a_i$ with $b_i$, the overall pool of numbers never changes, meaning $S$ is a constant invariant. 

To win, a player must secure a `1` at the Most Significant Bit (MSB) where $A$ and $B$ differ. Conveniently, the bits where $A$ and $B$ differ are exactly the bits where $S$ has a `1`! Therefore, the game is entirely decided by the MSB of $S$. 

**Who wins?** The player who controls the *last* possible opportunity to swap an element with a differing MSB. Because they have the final say, they can observe what the other player did and simply toggle the bit to guarantee they get the `1` and the opponent gets the `0`.

### 🪤 The Trap (What failed)
1. **0-Based vs 1-Based Indexing:** The problem states Ajisai moves on odd-numbered turns (1, 3, 5). If you store the arrays in standard 0-based Java arrays, Ajisai actually controls the **even** indices (0, 2, 4). You must check `last % 2 == 0` for Ajisai, not `% 2 == 1`!
2. **Checking the Wrong MSB:** You cannot just look at the MSB of all numbers in the arrays. You strictly only care about the MSB of the **XOR sum $S$**, because any bit where $S = 0$ means $A$ and $B$ will end up tied at that bit regardless of what the players do. 

### 🛠️ The Strategy
1. Calculate the global XOR sum $S$ of all elements in both arrays $a$ and $b$.
2. If $S == 0$, $A$ will always exactly equal $B$. Output `Tie`.
3. Find the position of the Most Significant Bit (MSB) in $S$. (You can do this by right-shifting $S$ until it hits 0, tracking the max index).
4. Scan the arrays **from right to left** (index $n-1$ down to 0).
5. Find the *first* index from the right (the *last* turn chronologically) where the MSB of $a_i$ is different from the MSB of $b_i$. 
6. If this `last` index is even (0-based), Ajisai controls the final decisive move. Output `Ajisai`. Otherwise, output `Mai`.

### ⏱️ Complexity
* **Time:** $O(N)$ (Three independent $O(N)$ passes: one to calculate $S$, one to find the MSB, and one reverse scan to find the last decisive index).
* **Space:** $O(N)$ (To store arrays $a$ and $b$).

</details>

<details>
<summary><b>D1. Removal of a Sequence (Easy Version)</b> | <code>Educational Codeforces Round 184 (Rated for Div. 2)</code> | </summary>

> **Link:** [Codeforces Problem](https://codeforces.com/problemset/problem/2169/D1)
> **Source Code:** [RemovalSequenceEasyVersion.java](https://github.com/AhmedRmadn/problem-solving/blob/master/src/codeforces/selected/RemovalSequenceEasyVersion.java)
> **Tags:** `binary search` , `implementation` , `math` , `number theory`

### 💡 The "Aha!" Moment
Instead of simulating the massive array shrinking forward, simulate your target index $K$ growing **backward** through time! 
Before an operation, the numbers are grouped in blocks of $Y$. The operation deletes the last element of every block, meaning each block shrinks to size $Y - 1$. 
If we know our target ends up at index $K$ *after* a deletion, we can figure out its index *before* the deletion by calculating how many deleted elements preceded it. Since every $Y - 1$ surviving elements represent $1$ deleted element, the number of deleted elements before our target is exactly $\lfloor (K - 1) / (Y - 1) \rfloor$. 
We just add those missing elements back to $K$ to find its previous position!

### 🪤 The Trap (What failed)
1. **Division by Zero:** If $Y = 1$, every single element in the array is deleted on the very first operation. The formula $(K - 1) / (Y - 1)$ will throw an arithmetic exception. You must manually check for $Y = 1$ and instantly output `-1`.
2. **The Upper Limit Ghost:** The original sequence strictly stops at $10^{12}$. If your backward-tracking $K$ ever exceeds $10^{12}$, it means the number that *would* end up at your target position didn't actually exist in the initial array. You must break out of the loop early to avoid Time Limit Exceeded (TLE) and output `-1`.

### 🛠️ The Strategy
1. **Edge Case:** If $y == 1$, print `-1` and `continue` to the next testcase.
2. **Reverse Simulation:** Loop $X$ times. In each step, update the index to its previous state: 
   `k = k + ((k - 1) / (y - 1))`
3. **Early Exit:** Add a condition to the `while` loop to immediately stop if $k > 10^{12}$. (Since $X$ is up to $10^5$, checking this prevents wasteful looping).
4. **Final Check:** After the loop, if $k \le 10^{12}$, print $k$ (since the initial array was just $1, 2, 3 \dots$, the value at index $k$ is exactly $k$). Otherwise, print `-1`.

### ⏱️ Complexity
* **Time:** $O(X)$ (A simple `while` loop running at most $X$ times. Because $X \le 10^5$ in the Easy version, this easily passes within the time limit).
* **Space:** $O(1)$ (Only storing a few primitive variables).

</details>


<details>
<summary><b>D2. Removal of a Sequence (Hard Version)</b> | <code>Educational Codeforces Round 184 (Rated for Div. 2)</code> | <code>10^12 Math Simulation</code></summary>

> **Link:** [Codeforces Problem](https://codeforces.com/contest/2169/problem/D2)
> **Source Code:** [RemovalSequenceEasyVersion.java](https://github.com/AhmedRmadn/problem-solving/blob/master/src/codeforces/selected/RemovalSequenceHardVersion.java)
> **Tags:** `binary search` , `implementation` , `math` , `number theory`

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
> **Source Code:** [TreeColoringEasyVersion.java](https://github.com/AhmedRmadn/problem-solving/blob/master/src/codeforces/selected/TreeColoringEasyVersion.java)
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

<details>
<summary><b>D. Rae Taylor and Trees (Easy)</b> | <code>Codeforces Round 1065 (Div. 3)</code> | <code>Math & Graph Connectivity</code></summary>

> **Link:** [Codeforces Problem](https://codeforces.com/problemset/problem/2171/D)
> **Source Code:** [RaeTaylorTreesEasyVersion.java](https://github.com/AhmedRmadn/problem-solving/blob/master/src/codeforces/selected/RaeTaylorTreesEasyVersion.java)
> **Tags:** `binary search` , `data structures` , `dp` , `dsu` , `greedy` , `implementation` , `trees`

### 💡 The "Aha!" Moment
To build a valid tree, the graph must be completely connected. The problem states that an edge can only exist between a left node $u$ and a right node $v$ if the left node's value is strictly smaller ($u < v$). 

What would make the tree impossible? If we can draw a vertical line anywhere in the array where **every number on the left is greater than every number on the right**. If this happens, no valid edges can ever cross that line from left to right, and the graph splits into two permanently disconnected components!

### 🪤 The Trap (What failed)
Because the problem mentions "graphs" and "trees", the immediate trap is trying to simulate building the tree using a Disjoint Set Union (DSU), DFS, or Segment Trees. This will overcomplicate the easy version. You don't need to build the tree; you only need to prove that the array doesn't have a "heavy left, light right" disconnect.

### 🛠️ The Strategy
How do we mathematically check for a disconnect without simulating the graph? 
1. Look at any suffix of the array of length `count`. 
2. If that suffix contains exactly the numbers $1, 2, \dots, \text{count}$, then its maximum value (`largest`) will be exactly equal to `count`.
3. If the right side contains $1$ through $k$, the left side *must* contain $k+1$ through $n$. This is the exact "heavy left, light right" scenario that breaks the graph!
4. Therefore, we just scan from right to left. We track the `largest` number seen in our suffix of length `count`. 
   * If `largest == count`, an isolated light group exists. Output `NO`.
   * If `largest > count`, the suffix contains a "heavy" number, meaning its "light" counterpart must be on the left side. This guarantees an edge can cross the boundary to connect them!

### ⏱️ Complexity
* **Time:** $O(N)$ (A single right-to-left pass tracking the maximum)
* **Space:** $O(N)$ (To store the array)

</details>

<details>
<summary><b>F. Rae Taylor and Trees (Hard)</b> | <code>Codeforces Round 1065 (Div. 3)s</code> | <code>Constructive Algorithms & Greedy</code></summary>

> **Link:** [Codeforces Problem](https://codeforces.com/contest/2171/problem/F)
> **Source Code:** [RaeTaylorTreesEasyVersion.java](https://github.com/AhmedRmadn/problem-solving/blob/master/src/codeforces/selected/RaeTaylorTreesHardVersion.java)
> **Tags:** `binary search` , `data structures` , `dp` , `dsu` , `greedy` , `implementation` , `trees`

### 💡 The "Aha!" Moment
The rule states: if we connect $u$ and $v$ (where $u < v$), $u$ **must** appear before $v$ in the permutation $p$. 

Because we process the array $p$ from left to right, any node $v$ we haven't processed yet is guaranteed to appear *after* our current node $u$. This means we can greedily connect $u$ to **every** unparented node $v$ that is strictly greater than $u$ ($v > u$). This forms a massive "star graph" radiating out from $u$, giving us the maximum possible forward "reach" into the right side of the array!

### 🪤 The Trap (What failed)
1. **The Disconnect Trap:** Just like the Easy version, you might encounter a node $u$ that hasn't been connected to anything yet. If the furthest index you've reached with your forward-reaching star edges is *before* your current index, the graph is permanently severed. You must output `NO`.
2. **The Bridging Trap:** If you find an unparented node $u$, but your forward reach extends *past* it, how do you pull $u$ into the tree? You can't connect it to a previous node because the rules might forbid it. The safest, guaranteed legal move is to connect $u$ to the node at your `lastReached` index. Since $u$ appears before `lastReached` in $p$, and $u$ failed to connect earlier (implying $u < p[\text{lastReached}]$), this edge is mathematically guaranteed to be valid!

### 🛠️ The Strategy
1. Track the locations of all elements using an `idx` array (where `idx[value] = position`).
2. Initialize a `parent` array with `-1` to track connections, and a `lastReached = -1` variable to track the furthest forward index we have successfully attached to our tree.
3. Iterate through $p$ from left to right. Let the current node be $u$ at index $i$.
4. **The Bridging Step:** If $u$ has no parent:
   * If `lastReached < i`, the graph is broken. Halt and output `NO`.
   * Otherwise, bridge the gap by setting `parent[u] = p[lastReached]`.
5. **The Star-Graph Step:** Now that $u$ is securely in the tree, connect it to as many future nodes as possible. Iterate through all values $v > u$. If $v$ has no parent, set `parent[v] = u` and update `lastReached = max(lastReached, idx[v])`. 
   *(Note: because we process sequentially, we can break this loop the second we hit a $v$ that already has a parent, keeping the time complexity strictly linear).*
6. If the loop completes successfully, output `YES` and print the edges from the `parent` array.

### ⏱️ Complexity
* **Time:** $O(N)$ (Although there is a nested loop, each node $v$ is assigned a parent exactly once and then skipped. The inner loop runs exactly $N$ times overall).
* **Space:** $O(N)$ (To store the `parent` and `idx` arrays).

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
> **Source Code:** [WarStrategy.java](https://github.com/AhmedRmadn/problem-solving/blob/master/src/codeforces/selected/WarStrategy.java)
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

<details>
<summary><b>B. Battle of Arrays</b> | <code>2025-2026 ICPC NERC</code> | <code>Game Theory & Priority Queues</code></summary>

> **Link:** [Codeforces Problem](https://codeforces.com/problemset/problem/2181/B)
> **Source Code:** [BattleOfArrays.java](https://github.com/AhmedRmadn/problem-solving/blob/master/src/codeforces/selected/BattleArrays.java)
> **Tags:** `data structures`, `games`, `greedy`

### 💡 The "Aha!" Moment & Game Analysis
The problem dictates that you **must** attack the opponent's maximum element. However, you can choose *any* element from your own array to attack with. 

**Why is choosing your max element optimal?** When you attack, your chosen element $x$ is **not consumed or weakened**. You are essentially just firing a laser of size $x$ at the opponent's element $y$. Because your goal is to destroy the opponent's array before they destroy yours, you want to deal the maximum possible damage every single turn. Dealing less damage gives the opponent a larger element to hit you back with on their next turn. Therefore, the greedy choice—always attacking with your max element—is strictly mathematically optimal.

### 🪤 The Trap (What failed)
**The "Simulation TLE" Illusion:** At first glance, a step-by-step simulation looks like it will fail. What if Alice's max is $1$, and Bob's max is $10^9$? Won't Alice take $10^9$ turns to reduce Bob's element, causing a TLE?
**No!** Because it is a *turn-based* game. 
1. Alice uses $1$ to hit Bob's $10^9$. Bob's element becomes $999,999,999$.
2. Now it is **Bob's turn**. Bob uses his $999,999,999$ to hit Alice's $1$. 
3. Since $999,999,999 \ge 1$, Alice's element is instantly destroyed!
The interleaved turns act like the Euclidean Algorithm for GCD. The sum of the maximums drops drastically, or an element is instantly vaporized. The simulation will never drag on.

### 🛠️ The Strategy
1. Load all of Alice's elements into a Max-Heap (Priority Queue).
2. Load all of Bob's elements into a Max-Heap.
3. **Simulate Alice's Turn:** * Peek at Alice's max ($x$). Poll Bob's max ($y$).
   * If $x < y$, Bob's element survives and is reduced to $y - x$. Push it back into Bob's heap. (If $x \ge y$, it is destroyed and not added back).
4. **Simulate Bob's Turn:**
   * If Bob's heap is empty, stop (Alice won).
   * Peek at Bob's max ($x$). Poll Alice's max ($y$).
   * If $x < y$, Alice's element survives and is reduced to $y - x$. Push it back into Alice's heap.
5. Repeat until one heap is empty. The winner is the one with a non-empty heap.

### ⏱️ Complexity
* **Time:** $O((N + M) \log(\text{MAX}) \log(N+M))$. Because of the Euclidean-like reduction on each alternating turn, elements are destroyed very quickly. Each priority queue operation takes logarithmic time.
* **Space:** $O(N + M)$ to store the elements in the two Priority Queues.

</details>

<details>
<summary><b>C. Dungeons</b> | <code>Codeforces Global Round 30 (Div. 1 + Div. 2)</code> | </summary>

> **Link:** [Codeforces Problem](https://codeforces.com/problemset/problem/2164/C)
> **Source Code:** [BattleOfArrays.java](https://github.com/AhmedRmadn/problem-solving/blob/master/src/codeforces/selected/Dungeon.java)
> **Tags:** `binary search` , `brute force` , `data structures` , `greedy` , `sortings`

### 💡 The "Aha!" Moment
Monsters fall into two categories:
1. **Renewable ($c_i > 0$):** Killing them gives you a sword back (potentially an even stronger one!). Your total ammo does not decrease.
2. **Terminal ($c_i = 0$):** Killing them permanently destroys a sword.

**The Greedy Order:** We should *always* fight Renewable monsters first. Since they don't consume our ammo count, fighting them can only maintain or upgrade our arsenal. Terminal monsters should be saved for the very end when we are ready to cash out our remaining swords for points. 
Within each category, we should fight the weakest monsters first (sorted by $b_i$ ascending) to ensure our current swords are strong enough to kill them and trigger the upgrades.

**The Weapon Choice:** When fighting a monster with health $b_i$, which sword should we use? We should use the **weakest possible sword that is still $\ge b_i$**. Wasting a massive sword on a weak monster might cost us a kill later.

### 🪤 The Trap (What failed)
**The "Max Sword" Trap:** If you just store your swords in a Max-Heap (Priority Queue) and always use your strongest sword, you will waste your heavy hitters on weak monsters. To efficiently find the *weakest valid sword*, you must use a Binary Search Tree structure. In Java, this is `TreeMap`, which gives us the magical $O(\log N)$ `ceilingKey()` function!

### 🛠️ The Strategy
1. **Store Swords:** Insert all initial swords into a `TreeMap<Long, Integer>` to track their frequencies. This allows us to find the optimal sword for any monster in $O(\log N)$ time.
2. **Sort Monsters:** Push all monsters into a `PriorityQueue` using a custom comparator:
   * Priority 1: Renewable ($c > 0$) comes before Terminal ($c = 0$).
   * Priority 2: Sort by required damage ($b_i$) ascending.
3. **Simulate the Battles:** Poll monsters one by one.
   * Ask the TreeMap for the smallest sword $\ge b_i$ (`a.ceilingKey(monsterB)`).
   * If no such sword exists, we can't kill this monster. Skip it.
   * If found, remove the sword from the map and increment our kill count.
   * If the monster was Renewable ($c > 0$), insert the new sword into the map with power $\max(\text{sword}, c_i)$.
4. Print the total kill count.

### ⏱️ Complexity
* **Time:** $O(M \log M + M \log N)$ (Sorting the $M$ monsters in the Priority Queue takes $M \log M$. For each of the $M$ monsters, querying and updating the `TreeMap` of $N$ swords takes $O(\log N)$).
* **Space:** $O(N + M)$ (To store the arrays, the Priority Queue, and the TreeMap).

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

<details>
<summary><b>C. Monopati</b> | <code>Codeforces Round 1063 (Div. 2)</code> | </summary>

> **Link:** [Codeforces Problem](https://codeforces.com/problemset/problem/2163/C)
> **Source Code:** [TheRoboticRush.java](https://github.com/AhmedRmadn/problem-solving/blob/master/src/codeforces/selected/Monopati.java)
> **Tags:** `brute force` , `combinatorics` , `dp` , `math` , `two pointers`

### 💡 The "Aha!" Moment
Instead of checking every possible $(l, r)$ pair to see if a path exists, we can reverse the logic: **look at the paths to see what pairs they allow!**

Because the grid is only 2 rows deep, any valid "down-right" path is entirely defined by a single choice: **the column $i$ where we drop from row 1 to row 2**. 
For a specific drop column $i$, the path uses cells `(1, 1)` to `(1, i)`, and then `(2, i)` to `(2, n)`. 
To make this exact path valid, our lower bound $l$ must be $\le$ the smallest number on the path, and our upper bound $r$ must be $\ge$ the largest number on the path. 
So, if we precalculate the Min and Max for every possible drop column $i$ (let's call them $L_i$ and $R_i$), any pair $(l, r)$ that completely encapsulates $[L_i, R_i]$ will successfully open path $i$!

### 🪤 The Trap (What failed)
1. **The $O(N^2)$ Brute Force:** Looping through all possible $(l, r)$ pairs and running a grid traversal will instantly result in a Time Limit Exceeded (TLE) since $N = 2 \cdot 10^5$.
2. **The "Strict $L$" Trap:** If a path requires bounds $[5, 10]$, then choosing $l=5$ and $r=10$ works. But choosing $l=4$ and $r=10$ **also** works! You cannot just map $L_i \to R_i$ and count them independently. If a path is unlocked by $L=5$, it is automatically unlocked for all $l \le 5$. You must propagate your minimum $R$ values backward using a sweep line.

### 🛠️ The Strategy
1. **Prefix & Suffix Arrays:** * Calculate `prefixMin` and `prefixMax` for Row 1.
   * Calculate `suffixMin` and `suffixMax` for Row 2.
2. **Find Path Constraints:** For every drop column $i$, calculate its strict requirements:
   * $L_i = \min(\text{prefixMin}[i], \text{suffixMin}[i])$
   * $R_i = \max(\text{prefixMax}[i], \text{suffixMax}[i])$
3. **Map the Best $R$:** Create an array `bestR` where `bestR[l]` stores the smallest (easiest to satisfy) $R$ requirement among all paths that have a lower bound requirement exactly equal to $l$.
4. **The Backwards Sweep:** Loop backwards from $2N$ down to $1$. 
   `bestR[l] = Math.min(bestR[l], bestR[l + 1])`
   *(This ensures that if $l=5$ has no paths, but $l=6$ unlocks a path requiring $R=10$, then $l=5$ can also use that path!).*
5. **Count Valid Pairs:** If `bestR[l]` is not infinity, any $r$ from `bestR[l]` up to $2N$ is a valid upper bound. Add `(2N - bestR[l] + 1)` to the total.

### ⏱️ Complexity
* **Time:** $O(N)$ (A few linear passes to build the prefix/suffix arrays and sweep the `bestR` array).
* **Space:** $O(N)$ (For the prefix, suffix, and `bestR` arrays).

</details>
