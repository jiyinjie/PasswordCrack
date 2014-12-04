1. UTEID: nbs439; yj3343;

FIRSTNAME: Nishil; Yinjie;

LASTNAME: Shah; Ji;

CSACCOUNT: nishil; jiyinjie;

EMAIL: nishil94@utexas.edu; jiyinjie@utexas.edu;

2.	The Password class is a class that stores all the parsed data from the 
	input file. Each line from the input file will be one Password. It provides
	a standard interface for accessing various variables like name, username,
	shell, etc.

	The PasswordCrack class which contains the main method performs a dictionary
	attack on the encrypted passwords given in the input file and stored in a 
	Password object. The main part of the attack takes place in the
	dictionaryAttack() method. The method first adds the usernames, first names,
	last names, and full names of all the users on the system to the dictionary.
	The attack then scans the dictionary to see if any exact matches were found 
	using just the dictionary. Then, the attack mangles each word in the
	dictionary and adds the mangled word to tne mangled_dictionary. 

	We mangle the word in several ways in the mangleWord() method. First, we
	check every permutation word capitalization of the given word. When given
	the word "michael", we check "Michael", "michaeL", "micHAEL", "MICHAEL",
	and every other permutation as well. The words that resulted from this
	capitilization permutation are not explicitly stored in our
	mangled_dictionary but are checked immediately against the encrypted
	passwords. We then append and prepend every printable ASCII character (from
	32 to 127), delete the first or last character, double the string (hi ->
	hihi), and reflect the string (hi -> hiih or hi->ihhi). These new strings
	are stored in the mangled_dictionary. We then traverse this new dictionary
	and check to see if any passwords are valid. We continue iterating until we
	find every possible which is unlikely.

3.	We are currently cracking 15/20 passwords in the passwd1 list and XX/20 in 
	the passwd2 list. This meets the 15 or so threshold outlined in the
	assignment details. Therefore, we believe that we met all requirements.

4.	We used the dictionary provided by Dr. Young, and that dictionary contains
	390 words.	


