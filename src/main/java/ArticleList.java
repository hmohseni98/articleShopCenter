
public class ArticleList {
    private Article[] list;
    private int emptyHomeIndex;

    public ArticleList() {
        list = new Article[1000];
        emptyHomeIndex = 0;
    }

    public void add(Article value) {
        list[emptyHomeIndex] = value;
        emptyHomeIndex++;
    }

    public Article get(int index) {
        return list[index];
    }


    public Boolean isEmpty() {
        return emptyHomeIndex == 0;
    }

    public int size() {
        return emptyHomeIndex;
    }

    public void add(int index, Article value) {
        // Check: Index invalid
        for (int i = emptyHomeIndex; i > index ; i--) {
            list[i] = list[i - 1];
        }
        list[index] = value;
        emptyHomeIndex++;
    }

    public void addAll(Article[] values) {
        for (Article v: values) {
            add(v);
        }
    }

    public void showList() {
        for (int i = 0; i < emptyHomeIndex; i++) {
            if (list[i] != null){
                System.out.print("ID:");
                System.out.print(list[i].getId());
                System.out.print("  ");
                System.out.print("Title:");
                System.out.print(list[i].getTitle());
                System.out.print("  ");
                System.out.print("Price:");
                System.out.print(list[i].getPrice());
                System.out.print("  ");
                System.out.print("Approved:");
                System.out.print(list[i].getApproved());
                System.out.println();
            }
            else {
                System.out.println("article does not exist!");
                break;
            }
        }
    }
    public void remove(int index){
        list[index] = null;
    }
    public void clear(){
        for (int i = 0; i < emptyHomeIndex; i++) {
            remove(i);
        }
    }
    public Boolean contains(Article number){
        boolean isContains = false;
        for (int i = 0; i < emptyHomeIndex; i++) {
            if (list[i] != null){
                if (list[i] == number) {
                    isContains = true;
                    break;
                }
            }
            else
                continue;
        }
        return isContains;
    }
}
