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
	CommandLine:
		java PasswordCrack words passwd1

	Output:
		TIMESTAMP: 52ms samantha's encrypted password is (bUx9LiAcW8As, and the decrypted password is amazing. (1 out of 20 found)
		TIMESTAMP: 102ms michael's encrypted password is atbWfKL4etk4U, and the decrypted password is michael. (2 out of 20 found)
		TIMESTAMP: 108ms maria's encrypted password is !cI6tOT/9D2r6, and the decrypted password is Salizar. (3 out of 20 found)
		TIMESTAMP: 397ms rachel's encrypted password is KelgNcBOZdHmA, and the decrypted password is obliqu3. (4 out of 20 found)
		TIMESTAMP: 661ms benjamin's encrypted password is %xPBzF/TclHvg, and the decrypted password is abort6. (5 out of 20 found)
		TIMESTAMP: 1440ms tyler's encrypted password is <qt0.GlIrXuKs, and the decrypted password is eeffoc. (6 out of 20 found)
		TIMESTAMP: 1821ms morgan's encrypted password is khnVjlJN3Lyh2, and the decrypted password is rdoctor. (7 out of 20 found)
		TIMESTAMP: 1840ms jennifer's encrypted password is e4DBHapAtnjGk, and the decrypted password is doorrood. (8 out of 20 found)
		TIMESTAMP: 2200ms connor's encrypted password is gwjT8yTnSCVQo, and the decrypted password is enoggone. (9 out of 20 found)
		TIMESTAMP: 2354ms evan's encrypted password is 3dIJJXzELzcRE, and the decrypted password is Impact. (10 out of 20 found)
		TIMESTAMP: 2500ms nicole's encrypted password is 7we09tBSVT76o, and the decrypted password is keyskeys. (11 out of 20 found)
		TIMESTAMP: 3183ms jack's encrypted password is jsQGVbJ.IiEvE, and the decrypted password is sATCHEL. (12 out of 20 found)
		TIMESTAMP: 3340ms alexander's encrypted password is feohQuHOnMKGE, and the decrypted password is squadro. (13 out of 20 found)
		TIMESTAMP: 3451ms victor's encrypted password is w@EbBlXGLTue6, and the decrypted password is THIRTY. (14 out of 20 found)
		TIMESTAMP: 3539ms james's encrypted password is {ztmy9azKzZgU, and the decrypted password is icious. (15 out of 20 found)
		TIMESTAMP: 3616ms abigail's encrypted password is &i4KZ5wmac566, and the decrypted password is liagiba. (16 out of 20 found)
		TIMESTAMP: 195387ms caleb's encrypted password is 8joIBJaXJvTd2, and the decrypted password is teserP. (17 out of 20 found)
		TIMESTAMP: 216241ms nathan's encrypted password is nxsr/UAKmKnvo, and the decrypted password is sHREWDq. (18 out of 20 found)

	CommandLine:
		java PasswordCrack words passwd2

	Output:
		TIMESTAMP: 71ms abigail's encrypted password is &ileDTgJtzCRo, and the decrypted password is Saxon. (1 out of 20 found)
		TIMESTAMP: 76ms hael's encrypted password is atQhiiJLsT6cs, and the decrypted password is tremors. (2 out of 20 found)
		TIMESTAMP: 1342ms evan's encrypted password is 3d1OgBYfvEtfg, and the decrypted password is ^bribed. (3 out of 20 found)
		TIMESTAMP: 1994ms morgan's encrypted password is kh/1uC5W6nDKc, and the decrypted password is dIAMETER. (4 out of 20 found)
		TIMESTAMP: 2217ms james's encrypted password is {zQOjvJcHMs7w, and the decrypted password is enchant$. (5 out of 20 found)
		TIMESTAMP: 2531ms tyler's encrypted password is <qf.L9z1/tZkA, and the decrypted password is eltneg. (6 out of 20 found)
		TIMESTAMP: 2795ms nicole's encrypted password is 7wKTWsgNJj6ac, and the decrypted password is INDIGNITY. (7 out of 20 found)
		TIMESTAMP: 4698ms jack's encrypted password is js5ctN1leUABo, and the decrypted password is ellows. (8 out of 20 found)
		TIMESTAMP: 4706ms caleb's encrypted password is 8jGWbU0xbKz06, and the decrypted password is zoossooz. (9 out of 20 found)
		TIMESTAMP: 4706ms benjamin's encrypted password is %xqFrM5RVA6t6, and the decrypted password is soozzoos. (10 out of 20 found)
		TIMESTAMP: 4940ms jennifer's encrypted password is e4EyEMhNzYLJU, and the decrypted password is ElmerJ. (11 out of 20 found)
		TIMESTAMP: 347062ms alexander's encrypted password is fe8PnYhq6WoOQ, and the decrypted password is Lacque. (12 out of 20 found)
		TIMESTAMP: 526054ms dustin's encrypted password is 5Wb2Uqxhoyqfg, and the decrypted password is Swine3. (13 out of 20 found)
		TIMESTAMP: 551784ms nathan's encrypted password is nxr9OOqvZjbGs, and the decrypted password is uPLIFTr. (14 out of 20 found)
		TIMESTAMP: 625493ms connor's encrypted password is gw9oXmw1L08RM, and the decrypted password is nosral. (15 out of 20 found)





