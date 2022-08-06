package model;

public class TableRow {
    private int clum;
    private int[] width = new int[0];
    private String[] content = new String[0];
    public TableRow(int clum, int[] with){
        setClum(clum);
        setWith(with);
        this.content = new String[clum+1];
    }

    public int getClum() {
        return clum;
    }

    public void setClum(int clum) {
        this.clum = clum;
    }

    public int[] getWith() {
        return width;
    }

    public void setWith(int[] with) {
        this.width = with;
    }

    public String[] getContent() {
        return content;
    }

    public void setContent(int indext,String conten) {
        int columWidth = width[indext-1];
        this.content[indext] = String.format("%-"+columWidth+"s",conten);
    }
    public void setCenterContent(int indext,String conten) {
        int columWidth;
        if (indext==0) columWidth = 5;
        else columWidth = width[indext-1];

        int a = (columWidth-conten.length())/2;
        int b = columWidth - a;
        this.content[indext] = String.format("%"+a+"s%-"+b+"s","",conten);
    }


    public void printTitle(String[] a){
        linePrint();
        System.out.printf("|%1s%-4s|","","STT");
        for (int i = 0; i<a.length;i++){
            int leng=a[i].length();
//            if (a[i].length()%2==0) leng = a[i].length();
//            else leng = a[i].length()+1;
            System.out.printf("%"+((width[i]-leng)/2)+"s%-"+(width[i]-((width[i]-leng)/2))+"s|","",a[i]);
        }
        System.out.print("\n");
        linePrint();
    }

    public void printConten(){

        for (String s : content) {
            System.out.print("|" + s);
        }
        System.out.print("|\n");
    }

    public void linePrint(){
        int tableWitdthLength = clum+5;
        for (int i : width){
            tableWitdthLength += i;
        }
        String border = new String("+"+new String(new char[tableWitdthLength])+"+").replace("\0","-");
        System.out.println(border);
    }
}
