package Main;

import java.awt.EventQueue;



public class Main {
	
	public static Window w;
	
	public static void main(String args[])
	{
		EventQueue.invokeLater(new Runnable() {
		public void run() {
			try {
				w= new Window();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	});
					
			
	}

}
/*
 
               BBBBBBBBBBBBBBBBB    MMMMMMMM               MMMMMMMM RRRRRRRRRRRRRRRRR   
               B::::::::::::::::B   M:::::::M             M:::::::M R::::::::::::::::R  
               B::::::BBBBBB:::::B  M::::::::M           M::::::::M R::::RRRRRR:::::R 
               BB:::::B     B:::::B M:::::::::M         M:::::::::M RR:::R     R:::::R
                 B::::B     B:::::B M::::::::::M       M::::::::::M R::::R     R:::::R
                 B::::B     B:::::B M:::::::::::M     M:::::::::::M R::::R     R:::::R
                 B::::BBBBBB:::::B  M:::::::M::::M   M::::M:::::::M R::::RRRRRR:::::R 
                 B:::::::::::::BB   M::::::M M::::M M::::M M::::::M R:::::::::::::RR  
                 B::::BBBBBB:::::B  M::::::M  M::::M::::M  M::::::M R::::RRRRRR:::::R 
                 B::::B     B:::::B M::::::M   M:::::::M   M::::::M R::::R     R:::::R
                 B::::B     B:::::B M::::::M    M:::::M    M::::::M R::::R     R:::::R
                 B::::B     B:::::B M::::::M     MMMMM     M::::::M R::::R     R:::::R
               BB:::::BBBBBB::::::B M::::::M               M::::::M R::::R     R:::::R
               B:::::::::::::::::B  M::::::M               M::::::M R::::::R   R:::::::R
               B::::::::::::::::B   M::::::M               M::::::M R::::::R   R:::::::R
               BBBBBBBBBBBBBBBBB    MMMMMMMM               MMMMMMMM RRRRRRRR   RRRRRRRRR
						 
							 _____      _               _____    _       _     
							| ___ \    (_)             |_   _|  (_)     | |    
							| |_/ /_ __ _  __ _ _ __     | |_ __ _ _ __ | |__  
							| ___ | '__| |/ _` | '_ \    | | '__| | '_ \| '_ \ 
							| |_/ | |  | | (_| | | | |   | | |  | | | | | | | |
							\____/|_|  |_|\__,_|_| |_|   \_|_|  |_|_| |_|_| |_|
                                 
 Features:
         
         Client to Client Communication
         Server to Clients Communication
         Mulitple clients
         

 */
