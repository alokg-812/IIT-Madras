TEMPLATE = """
Hello {name}

"""
def main():
    print(TEMPLATE.format(name="Alok"))

if __name__ == "__main__":
    main()

# output: Hello Alok
