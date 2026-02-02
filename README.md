<div align="center">
  <h1>🎬 VideoStore: High-Performance Benchmark</h1>
  <p><b>A Java-based transactional engine designed to evaluate the scalability of linear vs. hierarchical data structures.</b></p>

  <p>
    <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white" />
    <img src="https://img.shields.io/badge/Python-3776AB?style=for-the-badge&logo=python&logoColor=white" />
    <img src="https://img.shields.io/badge/Algorithms-Data%20Structures-blue?style=for-the-badge" />
  </p>
</div>

---

## 📌 Project Overview
The **VideoStore** program is a functional management system that allows for the storage and retrieval of videos and customers across four distinct data structures. It serves as a laboratory to observe how different algorithmic complexities (O(n) vs O(log n)) handle real-world business logic under extreme stress.

<h2 id="architecture">🏗️ Architecture & OOP Principles</h2>

<p>The program utilizes a <b>cohesive yet polymorphic design</b> to implement core OOP principles, ensuring high maintainability and code reusability. By decoupling the data structure logic from the business operations, the system can swap between linear and hierarchical collections seamlessly.</p>

<div align="center">
  <br>
  <img src="https://github.com/user-attachments/assets/72d7e0b7-131a-44ba-b18f-34ce360f18b1" width="650" alt="VideoStore UML Architecture">
  <p><i><b>Figure 2:</b> UML Class Diagram of the VideoStore System </i></p>
  <br>
</div>

<table width="100%">
  <tr>
    <td>
      <b>🔹 Interfaces (<code>LL</code>, <code>Tree</code>)</b><br>
      Defines the structural contract and behavior for all collection types.
    </td>
    <td>
      <b>🔹 Abstract Classes (<code>BST</code>)</b><br>
      Houses shared recursive logic while allowing for polymorphic method overriding.
    </td>
  </tr>
  <tr>
    <td>
      <b>🔹 Core Logic Implementations</b><br>
      Full support for Singly Linked Lists, Double Linked Lists, BST, and AVL Trees[cite: 4].
    </td>
    <td>
      <b>🔹 Polymorphic Management</b><br>
      The <code>VideoStore</code> manages <code>VideoCollection</code> objects, enabling high-level abstraction.
    </td>
  </tr>
</table>

---

## 📊 Performance Benchmarking
The program features a built-in **Synthetic Benchmark** mode. It executes a pre-defined pattern of requests determined by a **fixed seed**, ensuring that every data structure is tested against the exact same transactional workload for scientific accuracy.

### Stress Test Configuration
The benchmark scales through four tiers, culminating in a maximum load:
* **Videos:** 30,000
* **Customers:** 1,000
* **Requests:** 20,000 (Operations 5, 6, and 7)

<div align="center">
  <img width="1103" height="655" alt="Image" src="https://github.com/user-attachments/assets/8b2f62c5-678e-468c-ab44-846e78c115bd" />
  <p><i>Figure 2: Comparison of total service runtime (Averaged over 3 runs)</i></p>
</div>

### 🔍 Key Findings
| Metric | Linear (SLL/DLL) | Hierarchical (AVL) | Improvement |
|:--- |:--- |:--- |:--- |
| **Search Complexity** | O(n) | O(log n) | **Efficiency Jump** |
| **30k Stress Test** | ~9,300ms | ~3,100ms | **67% Faster** |



---

## ⚡ Technical Optimizations
* **Self-Balancing Logic:** The **AVL Tree** consistently outperformed the standard BST by **25%** at large scales, empirically proving the efficiency of maintaining strict height via rotations.
* **Recursion Stability:** To prevent a `StackOverflowError` during tree creation, objects are added to a temporary array and **shuffled** before being inserted into the BST/AVL.
* **Single-Pass Checkout:** Optimized operations to minimize traversals when moving videos between lists.

---

## 🛠️ Usage
The program is executed via the command line with the following syntax:

**Manual Interaction:**
```bash
java VideoStore [SLL|DLL|BST|AVL]

