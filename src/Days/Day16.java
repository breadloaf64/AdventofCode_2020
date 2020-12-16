package Days;

import Helpers.InputHandler;
import Helpers.Tuple;

import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Objects;

public class Day16 {

    public static void solve() throws IOException {
        ArrayList<String> input = InputHandler.get("src/Data/Day_16/test2.txt");

        TicketData td = new TicketData(input);
        int scanningErrorRate = td.scanningErrorRate();
        td.discardInvalidNearbyTickets();
        td.createTicketMatrix();
        td.deduceFieldIndex();
        //test(input);
    }


    private static void test(ArrayList<String> input) {

    }

    static class TicketData {
        Hashtable<String, ArrayList<Tuple<Integer, Integer>>> fieldRanges = new Hashtable();
        String myTicket = "";
        ArrayList<String> nearbyTickets = new ArrayList<>();
        ArrayList<String> indexFields = new ArrayList<>();
        Hashtable<String, Integer> fieldIndex = new Hashtable<>();
        int[][] ticketMatrix;
        int fieldCount;
        ArrayList<String> fields;

        TicketData(ArrayList<String> input) {
            int i = 0;

            while (!Objects.equals(input.get(i), "")) {
                String s;
                String field;
                String ranges;

                s = input.get(i);
                field = s.split(": ")[0];
                ranges = s.split(": ")[1];
                fieldRanges.put(field, validRanges(ranges));
                fields.add(field);
                i++;
            }
            i+=2;
            myTicket = input.get(i);
            i+=3;
            while (i < input.size()) {
                nearbyTickets.add(input.get(i));
                i++;
            }

            fieldCount = myTicket.split(",").length;
        }

        void createTicketMatrix() {
            ticketMatrix = new int[nearbyTickets.size()][fieldCount];
            int numberOfFields = myTicket.split(",").length;
            for (int r = 0; r < nearbyTickets.size(); r++) {
                String[] values = nearbyTickets.get(r).split(",");
                for (int c = 0; c < numberOfFields; c++) {
                    ticketMatrix[r][c] = Integer.parseInt(values[c]);
                }
            }
        }

        private ArrayList<Tuple<Integer, Integer>> validRanges(String s) {
            ArrayList<Tuple<Integer, Integer>> validRanges = new ArrayList<>();

            String[] ranges = s.split(" or ");
            int low = 0;
            int high = 0;
            for (int i = 0; i < ranges.length; i++) {
                low = Integer.parseInt(ranges[i].split("-")[0]);
                high = Integer.parseInt(ranges[i].split("-")[1]);
                validRanges.add(new Tuple<Integer, Integer>(low, high));
            }

            return validRanges;
        }

        int scanningErrorRate() {
            int scanningErrorRate = 0;
            for (String ticket : nearbyTickets) {
                scanningErrorRate += scanningErrorTicket(ticket);
            }
            return scanningErrorRate;
        }

        int scanningErrorTicket(String ticket) {
            int scanningErrorTicket = 0;
            String[] values = ticket.split(",");
            for (int i = 0; i < values.length; i++) {
                int value = Integer.parseInt(values[i]);
                if (!fieldRangesContainValue(value)) scanningErrorTicket+= value;
            }
            return scanningErrorTicket;
        }

        boolean fieldRangesContainValue(int value) {
            for (String field : fields) {
                if (rangesContainValue(fieldRanges.get(field), value)) return true;
            }
            return false;
        }

        boolean rangesContainValue(ArrayList<Tuple<Integer, Integer>> ranges, int value) {
            for (Tuple<Integer, Integer> range : ranges) {
                if (range.x <= value && value <= range.y) return true;
            }
            return false;
        }

        void discardInvalidNearbyTickets() {
            for(int i = nearbyTickets.size() - 1; i >= 0; i--) {
                if(scanningErrorTicket(nearbyTickets.get(i))> 0) {
                    nearbyTickets.remove(i);
                }
            }
        }

        void deduceFieldIndex() {
            Hashtable<String, ArrayList<Integer>> possibleIndices = new Hashtable<>();
            for(String field : fields) {
                possibleIndices.put(field, possibleIndicesForField(field));
            }
            System.out.println();
            while (!injective(possibleIndices)) {
                for (int i = 0; i < fieldCount; i++) {
                    if (possibleIndices.get(fields.get(i)).size() == 1) {
                        for (String field : fields) {
                            if (possibleIndices.get(field).size() > 0) {
                                possibleIndices.get(field).remove(Integer.valueOf(i));
                            }
                        }
                    }
                }
            }
            System.out.println();
            fieldIndex = new Hashtable<>();
            for (String field : fieldRanges.keySet()) {
                fieldIndex.put(field, possibleIndices.get(field).get(0));
            }
        }

        boolean injective(Hashtable<String, ArrayList<Integer>> possibleIndices) {
            for (String field : possibleIndices.keySet()) {
                if (possibleIndices.get(field).size() != 1) return false;
            }
            return true;
        }

        ArrayList<Integer> possibleIndicesForField(String field) {
            ArrayList<Integer> possibleIndicesForField = new ArrayList<>();
            for(int index = 0; index < fieldCount; index++) {
                if (validFieldIndexPair(field, index)) {
                    possibleIndicesForField.add(index);
                }
            }
            return possibleIndicesForField;
        }

        boolean validFieldIndexPair(String field, int index) {
            for (int i = 0; i < ticketMatrix.length; i++) {
                int valCheck = ticketMatrix[i][index];
                if(!rangesContainValue(fieldRanges.get(field), valCheck)) {
                    return false;
                }
            }
            return true;
        }

    }
}
