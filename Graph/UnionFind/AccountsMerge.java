import java.util.*;

class Solution {

    Map<String, String> parent = new HashMap<>();

    // Find with path compression
    private String find(String x) {
        if (!parent.get(x).equals(x)) {
            parent.put(x, find(parent.get(x)));
        }
        return parent.get(x);
    }

    // Union
    private void union(String a, String b) {
        parent.putIfAbsent(a, a);
        parent.putIfAbsent(b, b);

        String pa = find(a);
        String pb = find(b);

        if (!pa.equals(pb)) {
            parent.put(pa, pb);
        }
    }

    public List<List<String>> accountsMerge(List<List<String>> accounts) {

        Map<String, String> emailToName = new HashMap<>();

        // Step 1: Build unions
        for (List<String> acc : accounts) {
            String name = acc.get(0);
            String firstEmail = acc.get(1);

            for (int i = 1; i < acc.size(); i++) {
                String email = acc.get(i);
                emailToName.put(email, name);
                union(firstEmail, email);
            }
        }

        // Step 2: Group emails by root
        Map<String, List<String>> groups = new HashMap<>();

        for (String email : emailToName.keySet()) {
            String root = find(email);

                if (!groups.containsKey(root)) {
                  groups.put(root, new ArrayList<>());
              }

          groups.get(root).add(email);
        }

        // Step 3: Build result
        List<List<String>> result = new ArrayList<>();

        for (String root : groups.keySet()) {
            List<String> emails = groups.get(root);
            Collections.sort(emails);

            List<String> merged = new ArrayList<>();
            merged.add(emailToName.get(root));
            merged.addAll(emails);

            result.add(merged);
        }

        return result;
    }
}





# 🧠 Meaning of `parent` Map

```
parent[email] = who_is_my_leader
```

If:

```
parent[a] = b
parent[b] = c
parent[c] = c
```

Then ultimate leader = `c`.

---

# 📥 Example Input

```
["John", a, b]
["John", b, c]
["John", d]
["Mary", e]
["John", c, d]
```

---

# ▶ STEP 1: ["John", a, b]

Call:

```
union(a, b)
```

### Inside union:

```
parent[a] = a
parent[b] = b
```

Find roots:

```
find(a) → a
find(b) → b
```

Union:

```
parent[a] = b
```

### parent map

```
a → b
b → b
```

---

# ▶ STEP 2: ["John", b, c]

```
union(b, c)
```

Add missing:

```
parent[c] = c
```

Find roots:

```
find(b) → b
find(c) → c
```

Union:

```
parent[b] = c
```

### parent map

```
a → b
b → c
c → c
```

Chain formed: `a → b → c`

---

# ▶ STEP 3: ["John", d]

No union (only one email)

```
parent[d] = d
```

Map:

```
a → b
b → c
c → c
d → d
```

---

# ▶ STEP 4: ["Mary", e]

```
parent[e] = e
```

---

# ▶ STEP 5: ["John", c, d]

```
union(c, d)
```

Find:

```
find(c) → c
find(d) → d
```

Union:

```
parent[c] = d
```

Now map:

```
a → b
b → c
c → d
d → d
e → e
```

---

# 🧹 Now Path Compression Happens

When later we call:

```
find(a)
```

Execution:

```
find(a)
→ parent[a] = b → find(b)
→ parent[b] = c → find(c)
→ parent[c] = d → find(d)
→ d
```

While returning, it FLATTENS:

```
parent[a] = d
parent[b] = d
parent[c] = d
```

Final structure:

```
a → d
b → d
c → d
d → d
e → e
```

🔥 All of a,b,c,d now directly point to same root.

---

# ✅ What This Achieves

All emails belonging to same person have **same root**.

So when we group:

```
root d → [a,b,c,d]
root e → [e]
```

---

# 🎯 Why This Is Powerful

* Automatically handles chains
* Automatically handles cycles
* Near constant time

---


