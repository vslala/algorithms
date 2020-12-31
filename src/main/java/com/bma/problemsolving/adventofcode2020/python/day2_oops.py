class Policy:
    
    def __init__(self, policy):
        parts = policy.split(" ")
        bounds = parts[0].split("-")
        self.lower_bound, self.upper_bound, self.char = int(bounds[0]), int(bounds[1]), parts[1]

    def log(self):
        print(self.lower_bound, self.upper_bound, self.char)

    def apply(self, password):
        char_count = password.count_char(self.char)
        if self.lower_bound <= char_count <= self.upper_bound:
            password.is_valid_password = True

class NewPolicy(Policy):

    def apply(self, password):
        if password.char_at(self.lower_bound) == self.char == password.char_at(self.upper_bound):
            password.is_valid_password = False
        elif self.char in [password.char_at(self.lower_bound), password.char_at(self.upper_bound)]:
            password.is_valid_password = True 


class Password:

    def __init__(self, password):
        self.password = password
        self.is_valid_password = False
        
    def is_valid(self):
        return self.is_valid_password

    def count_char(self, char):
        return self.password.count(char)

    def char_at(self, pos):
        return self.password[pos]

file  = open("../inputs/day2.txt")
password_policy_mapper = {}

count = 0
for line in file:
    parts = line.split(":")
    policy = NewPolicy(parts[0])
    password = Password(parts[1])
    policy.apply(password)

    count += 1 if password.is_valid() else 0

print("Count: ", count)
