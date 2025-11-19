let n = Int(readLine() ?? "") ?? 0
let a = Set((readLine() ?? "").split { $0 == " " }.map { Int($0) ?? 0 })
let m = Int(readLine() ?? "") ?? 0
let b = (readLine() ?? "").split { $0 == " " }.map { Int($0) ?? 0 }

b.forEach {
    print(a.contains($0) ? "1" : "0")
}