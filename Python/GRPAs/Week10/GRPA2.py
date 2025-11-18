class StringManipulation:
    def __init__(self, words):
        self.words = [word.lower() for word in words]
        
    def total_words(self):
        return len(self.words)
        
    def count(self, some_word):
        return self.words.count(some_word.lower())
        
    def words_of_length(self, length):
        return [word for word in self.words if len(word) == length]
        
    def words_start_with(self, char):
        return [word for word in self.words if word.startswith(char.lower())]
        
    def longest_word(self):
        return max(self.words, key = len)
        
    def palindromes(self):
        return [word for word in self.words if word == word[::-1]]
