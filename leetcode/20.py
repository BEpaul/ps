class Solution:
    def isValid(self, s: str) -> bool:
            stack = []
            for p in s:
                if p == "(":
                    stack.append(")")
                elif p == "{":
                    stack.append("}")
                elif p == "[":
                    stack.append("]")
                elif not stack or stack.pop() != p:
                    return False
            
            return not stack