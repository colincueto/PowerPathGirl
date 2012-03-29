public class Initializer {
	MapList[] graph;
	Buildings[] bList;
	
	public void initData (Buildings[] bList, MapList[] graph){
		this.bList = bList;
		bList[0] = new Buildings("Molave Residence Hall", "Molave", "Dorm", "Residence Hall", "Molave2", 411, 148, 415, 135);
		bList[1] = new Buildings("Sanggumay Residence Hall", "Sanggumay", "Dorm", "Residence Hall", "Sanggumay2", 414, 112, 415, 113);
		bList[2] = new Buildings("Kalayaan Residence Hall", "Kalayaan", "Dorm", "Residence Hall", "Kalay", 418, 103, 415, 99);
		bList[3] = new Buildings("Yakal Residence Hall", "Yakal", "Dorm", "Residence Hall", "Yakal2", 424, 148, 415, 155);
		bList[4] = new Buildings("Ipil Residence Hall", "Ipil", "Dorm", "Residence Hall", "Ipil2", 501, 164, -1, -1);
		bList[5] = new Buildings("Ilang-ilang Residence Hall", "Ilang-ilang", "Dorm", "Residence Hall", "Ilang", 614, 77, 609, 82);
		bList[6] = new Buildings("Sampaguita Residence Hall", "Sampaguita", "Dorm", "Residence Hall", "Sampa", 545, 405, 524, 408);
		bList[7] = new Buildings("Kamia Residence Hall", "Kamia", "Dorm", "Residence Hall", "Kamia2", 490, 405, 506, 408);
		bList[8] = new Buildings("Centennial Dormitory", "Centennial", "Dorm", "Cente", "Cente2", 125, 625, 124, 636);
		bList[9] = new Buildings("International Center", "IC", "International", "Foreigners", "Koreans", 612, 129, 611, 131);
		bList[10] = new Buildings("University Theatre", "Univ Theatre", "Univ", "Theater", "Theatre", 302, 202, 285, 193);
		bList[11] = new Buildings("Consumers Cooperative", "Coop", "Grocery", "Market", "Cooperative", 439, 57, 455, 75);
		bList[12] = new Buildings("Shopping Center", "Shopping", "Computer Shop", "Rodics", "SC", 504, 64, 498, 75);
		bList[13] = new Buildings("Accounting Office", "Cashier", "PNB", "Bank", "ATM", 501, 55, -1, -1);
		bList[14] = new Buildings("Bahay Ng Alumni", "BNA", "Chokiss", "Veterans", "Alumni Center", 325, 152, 328,156);
		bList[15] = new Buildings("Main Library", "MainLib", "Library", "Bulwagan ng Dangal", "University Library", 522, 288, -1, -1);
		bList[16] = new Buildings("Office of the University Registrar", "OUR", "Registrar", "Enrol", "Form5", 335, 455, 333, 456);
		bList[17] = new Buildings("Vinzons Hall", "Vinzons", "STFAP", "ATM", "USC", 624, 315, 622, 317);
		bList[18] = new Buildings("Quezon Hall", "Oblation", "QH", "Ampitheater", "Oble", 245, 290, 217, 292);
		bList[19] = new Buildings("Vargas Museum", "Vargas", "Museum", "Cafe Iana", "Art", 338, 344, 336, 344);
		bList[20] = new Buildings("College of Arts and Sciences Alumni Association Food Center", "CASAA", "Food", "Cafeteria", "Canteen", 518, 371, -1, -1);
		bList[21] = new Buildings("Computer Center", "CC", "Webmail", "Linux", "CRS", 492, 164, -1, -1);
		bList[22] = new Buildings("Faculty Center", "FC", "Hardin ng Diwata", "THY", "Faculty", 419, 362, 418, 342);
		bList[23] = new Buildings("University of the Philippines Integrated School", "UPIS", "High School", "Elementary", "IS", 562, 394, 560, 406);
		bList[24] = new Buildings("National Engineering Center", "NEC", "Juinio Hall", "Microsoft", "NEC2", 557, 208, 535, 233);
		bList[25] = new Buildings("University Health Service", "Infirmary", "Hospital", "Health Service", "UHS", 538, 68, 533, 72);
		bList[26] = new Buildings("Universiy Hotel", "UnivHotel", "Hotel", "Lodging", "UnivHotel2", 602, 7, -1, -1);
		bList[27] = new Buildings("Palma Hall", "AS", "Palma", "Arts and Sciences", "CAS", 472, 340, 420, 340);
		bList[28] = new Buildings("College of Arts and Letters", "CAL", "CNB", "5th floor", "KAL", 370, 380, -1, -1);
		bList[29] = new Buildings("Palma Hall Annex", "Annex", "PHAN", "PH Annex", "PHAN2", 560, 340, 538, 338);
		bList[30] = new Buildings("College of Education", "Eduk", "Educ", "Education", "Teachers", 580, 340, 583, 338);
		bList[31] = new Buildings("Chemistry Pavillion", "ChemPav", "Pav", "Chem", "Chemistry", 430, 405, 429, 408);
		bList[32] = new Buildings("Biology Pavillion", "BioPav", "Pav", "Bio", "Biology", 510, 405, 504, 408);
		bList[33] = new Buildings("Physics Pavillion", "PhysicsPav", "Pav", "Physics", "P6", 475, 405, 463, 409);
		bList[34] = new Buildings("Institute of Biology", "IB", "Bio", "Biology", "Bio2", 525, 405, 545, 407);
		bList[35] = new Buildings("College of Home Economics", "HE", "HomeEcon", "Home Econ", "CHE", 600, 440, -1, -1);
		bList[36] = new Buildings("College of Fine Arts", "CFA", "Fine Arts", "FA", "Art", 75, 395, 125, 386);
		bList[37] = new Buildings("Molecular Biology and Biotechnology", "MBB", "Bio", "Albert", "Albert Hall", 135, 385, 151, 385);
		bList[38] = new Buildings("College of Architecture", "Archi", "Arki", "Architecture", "Arki2", 285, 465, 281, 464);
		bList[39] = new Buildings("National College of Public Administration and Governance", "NCPAG", "Public Ad", "Administration", "PublicAd", 125, 195, 126, 157);
		bList[40] = new Buildings("College of Human Kinetics", "CHK", "PE", "Gym", "Human Kinetics", 168, 108, 153, 157);
		bList[41] = new Buildings("Asian Institute of Tourism", "AIT", "Tour", "Tourism", "Tour2", 77, 15, -1, -1);
		bList[42] = new Buildings("Vanguard Hall", "Vanguard", "Vanguard2", "Vanguard3", "Vanguard4", 245, 105, -1, -1);
		bList[43] = new Buildings("College of Social Work and Community Development", "CSWCD", "Social Work", "Comm Dev", "CommDev", 190, 160, 190, 159);
		bList[44] = new Buildings("College of Mass Communication", "CMC", "Maskom", "Mass Comm", "MassComm", 240, 200, 240, 239);
		bList[45] = new Buildings("College of Music", "Music", "Abelardo", "Abelardo Hall", "AH", 246, 210, 242, 163);
		bList[46] = new Buildings("University of the Philippines Film Institute", "FI", "Film", "Film Insti", "Theater", 320, 235, 313, 235);
		bList[47] = new Buildings("Asian Center", "Asian Center", "Romulo Hall", "Center for Islamic Studies", "Romulo", 615, 180, 610, 164);
		bList[48] = new Buildings("College of Engineering", "Engg", "Eng'g", "Engineering", "Melchor", 465, 230, 416, 232);
		bList[49] = new Buildings("School of Statistics", "Statistics", "Stat", "School", "Ew", 520, 160, -1, -1);
		bList[50] = new Buildings("College of Law", "Law", "Law2", "Law3", "Law4", 609, 215, 610, 212);
		bList[51] = new Buildings("College of Economics", "Econ", "Economics", "Econ2", "Econ3", 617, 232, 611, 234);
		bList[52] = new Buildings("College of Business Administration", "BA", "CBA", "Business Ad", "BAA", 631, 273, 630, 272);
		bList[53] = new Buildings("National Institute for Science and Mathematics Education Development", "Information Technology Training Center", "ISMED", "NISMED", "ITTC", 418, 426, 420, 409);
		bList[54] = new Buildings("Department of Computer Science", "DCS", "AECH", "Com Sci", "Engg Lib II", 435, 600, 435, 579);
		bList[55] = new Buildings("National Institute of Geological Sciences", "NIGS", "Geol", "Geology", "PIGS", 437, 616, 434, 610);
		bList[56] = new Buildings("Institute of Mathematics Building", "iMath", "MBAN", "MB", "Math", 542, 629, 523, 604);
		bList[57] = new Buildings("College of Science", "ATM", "CS", "Science", "CS Lib", 433, 566, 427, 561);
		bList[58] = new Buildings("National Institute of Physics", "Physics", "p6","NIP", "Physics Building", 645, 597, -1, -1);
		bList[59] = new Buildings("Electrical and Electronics Engineering Institute", "EEE", "iEEE", "triple E", "Department of Electrical and Electronics Engineering", 416, 531, 416, 528);
		bList[60] = new Buildings("Marine Science Institute", "MSI", "Marine", "Marine Science", "MS", 410, 495, 408, 499);
		bList[61] = new Buildings("Computational Science Research Center", "CSRC", "Science", "CS Ampitheater", "MST", 538, 552, -1, -1);
		bList[62] = new Buildings("Institute of Chemistry", "IC", "Chem", "Chem Building", "Malayong Chem", 625, 536, -1, -1);
		
		this.graph = graph;
		for (int i = 0; i < graph.length; i++){
			graph[i] = new MapList();
		}
		connect(0,1); connect(0,3); connect(0,14); connect(0,48);
		connect(1,2); connect(2,3); connect(2,11); connect(3,4); connect(3,48);
		connect(4,21); connect(4,49); connect(5,9); connect(5,25);
		connect(6,7); connect(6,32); connect(7,33); connect(8,36); 
		connect(9,47); connect(10,14); connect(10,44); connect(10,46); connect(11,12);
		connect(12,13); connect(12,25); connect(12,49); connect(14,42); 
		connect(15,24); connect(15,29); connect(16,38); connect(16,53);
		connect(17,30); connect(17,52); connect(18,19); 
		connect(18,44); connect(18,46); connect(19,22); connect(19,28);
		connect(20,29); connect(20,32); connect(21,48); 
		connect(22,27); connect(22,28); connect(22,31); connect(23,30); connect(23,34);
		connect(23,35); connect(24,48); connect(24,50); connect(25,26); 
		connect(27,29); connect(27,48); connect(29,30);
		connect(31,33); connect(31,53); connect(32,33); 
		connect(32,34); connect(36,37); connect(36,39); connect(37,38);
		connect(39,40); connect(39,43); connect(40,41); connect(40,42); 
		connect(43,44); connect(44,45); connect(45, 46); connect(46,48);
		connect(47,49); connect(47,50); connect(47,51); connect(51,52); 
		connect(53,60); connect(54,55); connect(54,57); connect(55,56);
		connect(56,57); connect(56,58); connect(57,59); connect(57,61); 
		connect(58,62); connect(59,60); connect(61,62);
	}
	
	public void connect(int a, int b){
		graph[a].addNode(b, a, bList);
		graph[b].addNode(a, b, bList);
	}
}
