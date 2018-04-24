/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication2;

/**
 *
 * @author andy
 */
public class TestPolyphony {

    int ScannedKeys[] = new int[4];
    int CurrentPoly = 4;

    int ActualKeysPressed[] = {-1, -1, -1, -1};
    int AssignedKeyPressed[] = {-1, -1, -1, -1};
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        TestPolyphony tp = new TestPolyphony();

    }

    TestPolyphony() {
        ScankeyBoard(); //Keys are in Scanned Keys

        AssignKeys();
        
        ActualKeysPressed[0] = 5;
        ScankeyBoard();

        AssignKeys();
        
        ActualKeysPressed[1] = 6;
        ScankeyBoard();

        AssignKeys();
     
        ActualKeysPressed[2] = 20;
        ScankeyBoard();

        AssignKeys();
        
        ActualKeysPressed[2] = 22;
        ScankeyBoard();

        AssignKeys();
        ActualKeysPressed[0] = -1;
        ScankeyBoard();
    
        AssignKeys();
        
        ActualKeysPressed[0] = 30;
        ScankeyBoard();
    
        AssignKeys();
        
    }

    void AssignKeys() {
        int newKeyPressed[] = {-1, -1, -1, -1};
        int CurrentFinger = -1;
        //Test out last keys to current one

        int Key = -1;
        //Find notes this time the same as last and keep a list
        int KeptFingers[] = {-1, -1, -1, -1};
        int newKeys[] = {-1, -1, -1, -1};

        FindKeptFingers(KeptFingers);

        FindNewNotes(newKeys);
        //report(KeptFingers,newKeys);
        
        SetKeptKeys(KeptFingers,newKeyPressed);
        SetNewKeys(newKeys,newKeyPressed);
        
        for (int k = 0; k < 4; k++) {
            AssignedKeyPressed[k]=newKeyPressed[k];
        }
        
        System.out.println("Assigned Keys   ");
        for (int k = 0; k < 4; k++) {
            System.out.print("" + AssignedKeyPressed[k]+" , ");

        }
        System.out.println("");
    }

    
    void report(int KeptFingers[],int newKeys[]){
         System.out.println("Actual keys");
        for (int k = 0; k < 4; k++) {
            
            System.out.print(k+ " ,"+ActualKeysPressed[k]+" :  ");
        }
        System.out.println("");
        System.out.println("Assigned keys");
        for (int k = 0; k < 4; k++) {
            
            System.out.print(k+ " ,"+AssignedKeyPressed[k]+" :  ");
        }
        System.out.println("");
        System.out.println("ScannedKeys");
        for (int k = 0; k < 4; k++) {
            
            System.out.print(k+ " ,"+ScannedKeys[k]+" :  ");
        }
        System.out.println("");
        System.out.println("Kept Keys");
        for (int k = 0; k < 4; k++) {
            if (KeptFingers[k]!=-1)
            System.out.print(" "+KeptFingers[k]+" , "+AssignedKeyPressed[KeptFingers[k]]+" :  ");
        }
        System.out.println("");
        System.out.println("New Keys");
        for (int k = 0; k < 4; k++) {
            if (newKeys[k]!=-1)
            System.out.print(" "+newKeys[k]+" , "+ScannedKeys[newKeys[k]]+" :  ");
        }
        System.out.println("");
    }
    
    void SetNewKeys(int newKeys[],int newKeyPressed[]){
        for (int k = 0; k < 4; k++) {
            if (newKeys[k] != -1) {
                for (int j = 0; j < 4; j++) { //find a free slot
                    if (newKeyPressed[j] == -1) {
                        newKeyPressed[j] = ScannedKeys[newKeys[k]];
                        break;
                    }
                }
            }
        }
    }
    
    void SetKeptKeys(int KeptFingers[],int newKeyPressed[]){
        for (int k = 0; k < 4; k++) {
            if (KeptFingers[k] != -1) {
                newKeyPressed[KeptFingers[k]] = AssignedKeyPressed[KeptFingers[k]];
            }
        }
    }
    
    void FindNewNotes(int newKeys[]){
        //Find new notes
        int ptrNew = 0;
        int Key=-1;
        for (int i = 0; i < CurrentPoly; i++) {
            Key = ScannedKeys[i];
            if (Key != -1) {
                boolean Found = false;
                for (int j = 0; j < CurrentPoly; j++) {
                    if (Key == AssignedKeyPressed[j]) {
                        Found = true;
                        break; //Go onto next key
                    }

                }
                if (Found == false) {
                    //System.out.println(ptrKept + "   Key " + Key);
                    newKeys[ptrNew] = i;
                    ptrNew++;
                }
            }
        }
    }
    
    void FindKeptFingers(int KeptFingers[]){
        int Key=-1;
        int ptrKept = 0;//Find notes this time the same as last and keep a list
        for (int i = 0; i < CurrentPoly; i++) {
            Key = AssignedKeyPressed[i];
            for (int j = 0; j < CurrentPoly; j++) {
                if ((Key == ScannedKeys[j]) && (ScannedKeys[j]!=-1)) {
                    KeptFingers[ptrKept] = i;
                    ptrKept++;
                    break; //Go onto next key
                }
            }
        }
    }
    
    //Key Pressed data is in Currentkeys[];
    void ScankeyBoard() {
        int Count = 0;
        int Keys[] = new int[4];
        for (int k = 0; k < 4; k++) {
            ScannedKeys[k] = -1;
        }
        for (int i = 0; i < 8; i++) {

            for (int j = 0; j < 5; j++) {

                int Key = (8 * j) + i;
                //System.out.println(Key);
                for (int k = 0; k < CurrentPoly; k++) {
                    if (k < ActualKeysPressed.length) {
                        if (Key == ActualKeysPressed[k]) {
                            Keys[Count] = Key;
                            Count++;
                            //insert in order
                            for (int l = 0; l < CurrentPoly; l++) {
                                if (Key > ScannedKeys[l]) {
                                    if (ScannedKeys[l] == -1) {
                                        ScannedKeys[l] = Key;
                                        l = CurrentPoly;
                                    }
                                } else {
                                    //Insert it by moving everything up one
                                    for (int m = (CurrentPoly - 2); m >= l; m--) {
                                        ScannedKeys[m + 1] = ScannedKeys[m];
                                    }
                                    ScannedKeys[l] = Key;
                                    l = CurrentPoly;
                                }

                            }
                        }
                    }
                }

            }
        }
        //System.out.println("---------------");
        //System.out.println("Keys[k]     ScannedKeys[k]");
        //for (int k = 0; k < 4; k++) {
        //    System.out.println(Keys[k] + "   " + ScannedKeys[k]);
        //}
    }
}
