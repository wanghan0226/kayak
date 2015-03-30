    package util;

    import beans.Ticket;

    import java.util.Collection;
    import java.util.Collections;
    import java.util.Comparator;
    import java.util.List;

    /**
     * @author Yun
     */
    public class SortTicket {

        /**
         * This function use to sort the ticket by Price
         * @param tickets: is a list of ticket.
         * @return: a list of flight which sorted in ascending order by price
         */
        public static List<Ticket> sortTicketByPriceAscending(List<Ticket> tickets){
            Comparator<Ticket> c = new Comparator<Ticket>() {
                @Override
                public int compare(Ticket t1, Ticket t2) {
                    if(t1.getPrice() - t2.getPrice() > 0){
                        return 1;
                    }else if(t1.getPrice() - t2.getPrice() < 0 ){
                        return -1;
                    }else {
                        return 0;
                    }
                }
            };
            Collections.sort(tickets, c);
            return  tickets;
        }

        /**
         * This function use to sort the ticket by Price
         @param tickets: is a list of ticket.
          * @return: a list of flight which sorted in deascending order by price
         */
        public static List<Ticket> sortTicketByPriceDescending(List<Ticket> tickets){
            Comparator<Ticket> c = new Comparator<Ticket>() {
                @Override
                public int compare(Ticket t1, Ticket t2) {
                    if(t1.getPrice() - t2.getPrice() < 0){
                        return 1;
                    }else if(t1.getPrice() - t2.getPrice() > 0 ){
                        return -1;
                    }else {
                        return 0;
                    }
                }
            };
            Collections.sort(tickets, c);
            return  tickets;
        }

        /**
         * This function use to sort the ticket by Duration
         * @param tickets: is a list of ticket.
          * @return: a list of flight which sorted in deascending order by duration
         */
        public static List<Ticket> sortTicketByDurationAscending(List<Ticket> tickets){
            Comparator<Ticket> c = new Comparator<Ticket>() {
                @Override
                public int compare(Ticket t1, Ticket t2) {
                    if(t1.getTimeInterval() - t2.getTimeInterval() > 0 ){
                        return  1;
                    }else if(t1.getTimeInterval() == t2.getTimeInterval()){
                        return 0;
                    }else{
                        return -1;
                    }
                }
            };
            Collections.sort(tickets,c);
            return tickets;
        }


        /**
         * This function use to sort the ticket by Duration
         *@param tickets: is a list of ticket.
          * @return: a list of flight which sorted in deascending order by duration
         */
        public static List<Ticket> sortTicketByDurationDescending(List<Ticket> tickets){
            Comparator<Ticket> c = new Comparator<Ticket>() {
                @Override
                public int compare(Ticket t1, Ticket t2) {
                    if(t1.getTimeInterval() - t2.getTimeInterval() > 0 ){
                        return  -1;
                    }else if(t1.getTimeInterval() == t2.getTimeInterval()){
                        return 0;
                    }else{
                        return 1;
                    }
                }
            };
            Collections.sort(tickets,c);
            return tickets;
        }

    }
