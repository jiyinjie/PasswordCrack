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

	We mangle the word in several ways in the mangleWord() method. We change
	the string's capitalization in various ways. We then append and prepend 
	every printable ASCII character (from 32 to 127), delete the first or last
	character, double the string (hi -> hihi), and reflect the string (hi -> 
	hiih or hi->ihhi). These new strings are tored in the mangled_dictionary. 
	We then traverse this new dictionary and check to see if any passwords are 
	valid. We continue iterating until we find every possible which is unlikely.
	Therefore, the program will appear to infinitely loop.

3.	We are currently cracking 18/20 passwords in the passwd1 list and /20 in 
	the passwd2 list. This meets the 15 or so threshold outlined in the
	assignment details. Therefore, we believe that we met all requirements.

4.	We used the dictionary provided by Dr. Young, and that dictionary contains
	390 words.
	passwd1 list (output):
	TIMESTAMP: 33ms samantha's encrypted password is (bUx9LiAcW8As, and the decrypted password is amazing. (1 out of 20 found)
	TIMESTAMP: 111ms michael's encrypted password is atbWfKL4etk4U, and the decrypted password is michael. (2 out of 20 found)
	TIMESTAMP: 121ms maria's encrypted password is !cI6tOT/9D2r6, and the decrypted password is Salizar. (3 out of 20 found)
	TIMESTAMP: 227ms rachel's encrypted password is KelgNcBOZdHmA, and the decrypted password is obliqu3. (4 out of 20 found)
	TIMESTAMP: 318ms benjamin's encrypted password is %xPBzF/TclHvg, and the decrypted password is abort6. (5 out of 20 found)
	TIMESTAMP: 1542ms tyler's encrypted password is <qt0.GlIrXuKs, and the decrypted password is eeffoc. (6 out of 20 found)
	TIMESTAMP: 2144ms morgan's encrypted password is khnVjlJN3Lyh2, and the decrypted password is rdoctor. (7 out of 20 found)
	TIMESTAMP: 2175ms jennifer's encrypted password is e4DBHapAtnjGk, and the decrypted password is doorrood. (8 out of 20 found)
	TIMESTAMP: 2742ms connor's encrypted password is gwjT8yTnSCVQo, and the decrypted password is enoggone. (9 out of 20 found)
	TIMESTAMP: 2973ms evan's encrypted password is 3dIJJXzELzcRE, and the decrypted password is Impact. (10 out of 20 found)
	TIMESTAMP: 3204ms nicole's encrypted password is 7we09tBSVT76o, and the decrypted password is keyskeys. (11 out of 20 found)
	TIMESTAMP: 4257ms jack's encrypted password is jsQGVbJ.IiEvE, and the decrypted password is sATCHEL. (12 out of 20 found)
	TIMESTAMP: 4506ms alexander's encrypted password is feohQuHOnMKGE, and the decrypted password is squadro. (13 out of 20 found)
	TIMESTAMP: 4667ms victor's encrypted password is w@EbBlXGLTue6, and the decrypted password is THIRTY. (14 out of 20 found)
	TIMESTAMP: 4803ms james's encrypted password is {ztmy9azKzZgU, and the decrypted password is icious. (15 out of 20 found)
	TIMESTAMP: 4922ms abigail's encrypted password is &i4KZ5wmac566, and the decrypted password is liagiba. (16 out of 20 found)
	TIMESTAMP: 275663ms caleb's encrypted password is 8joIBJaXJvTd2, and the decrypted password is teserP. (17 out of 20 found)
	TIMESTAMP: 308242ms nathan's encrypted password is nxsr/UAKmKnvo, and the decrypted password is sHREWDq. (18 out of 20 found)



