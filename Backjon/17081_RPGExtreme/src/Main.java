import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Objects;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        GameMaster gm = new GameMaster(N, M);
        
        int boxSize = 0;
        int enemySize = 0;

        // 게임판 만들기, 몬스터랑 아이템 갯수 세기, 주인공 자리 체크
        for(int i = 0; i < N; i++){
            char[] cl = bf.readLine().toCharArray();
            for(int j = 0; j < M; j++){
                if(cl[j] == 'B'){
                    boxSize ++;
                }else if(cl[j] == '&' || cl[j] == 'M'){
                    enemySize ++;
                }else if(cl[j] == '@'){
                    Position p = new Position(i, j);
                    gm.startPosition = p;
                    cl[j] = '.';
                }
            }

            gm.gameBoard[i] = cl;
        }

        //명령어 라인 입력
        char[] commandLine = bf.readLine().toCharArray();
        gm.commandLine = commandLine;

        // 몬스터 정보 입력
        for(int i = 0; i < enemySize; i++){
            st = new StringTokenizer(bf.readLine());
            
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            String name = st.nextToken();
            int atk = Integer.parseInt(st.nextToken());
            int arm = Integer.parseInt(st.nextToken());
            int hp = Integer.parseInt(st.nextToken());
            int exp = Integer.parseInt(st.nextToken());
            
            Position p = new Position(x, y);
            Monster m = new Monster(name, atk, arm, hp, exp);
            
            gm.monsters.put(p, m);
        }
        
        // 아이템 정보 입력
        for (int i = 0; i < boxSize; i++){
            st = new StringTokenizer(bf.readLine());
            
            int x = Integer.parseInt(st.nextToken()) -1;
            int y = Integer.parseInt(st.nextToken()) -1;
            String itemm = st.nextToken();
            String spec = st.nextToken();
            
            Position p = new Position(x, y);
            Item ii = new Item(itemm, spec);

            gm.items.put(p, ii);
        }

        // 게임 스타트
        String answer = gm.runGame();
        
        // 결과 출력
        System.out.println(answer);
    }
}

class GameMaster{
    char[][] gameBoard;
    char[] commandLine;
    int commandIndex;

    Player player;
    Position playerPosition;
    Position startPosition;
    
    HashMap<Position, Monster> monsters;
    HashMap<Position, Item> items;

    GameMaster(int n, int m){
        player = new Player();
        commandIndex = 0;

        gameBoard = new char[n][m];

        monsters = new HashMap<>();
        items = new HashMap<>();
    }

    //test
    public String debugGame(){
        StringBuilder sb = new StringBuilder();
        
        sb.append("Command : ").append(commandLine[commandIndex - 1]).append("\n");
        sb.append(String.format("position : x.%d   y.%d\n", playerPosition.posX, playerPosition.posY));
        for(int i = 0; i < gameBoard.length; i++){
            for(int j = 0; j< gameBoard[0].length; j++){
                if (i == playerPosition.posX && j == playerPosition.posY){
                    sb.append("@");
                }else{
                    sb.append(gameBoard[i][j]);  
                }
            }
            sb.append("\n");
        }

        sb.append("Passed Turns : ").append(commandIndex).append("\n");

        sb.append("\n");
        return sb.toString();
    }
    
    public String runGame(){
        playerPosition = startPosition.copyIt();
        boolean isGameOver = false;
        
        while(commandIndex < commandLine.length){
            int x = 0;
            int y = 0;
            switch (commandLine[commandIndex++]) {
                case 'L':
                    y = -1;
                    break;
                case 'R':
                    y = 1;
                    break;
                case 'U':
                    x = -1;
                    break;
                case 'D':
                    x = 1;
                    break;
            }
            int moveX = playerPosition.posX + x;
            int moveY = playerPosition.posY + y;
            
            boolean boardOut = false;
            // 이동 시 보드 나가면 무효
            if (!(0 <= moveX && moveX < gameBoard.length && 0 <= moveY && moveY < gameBoard[0].length)){
                boardOut = true;
            }

            // 이동 시의 구역 체크 후 알맞은 행동 실행
            char place;
            if (boardOut || gameBoard[moveX][moveY] == '#'){
                moveX = playerPosition.posX;
                moveY = playerPosition.posY;
            }
            place = gameBoard[moveX][moveY];

            playerPosition.posX = moveX;
            playerPosition.posY = moveY;
            
            switch (place) {
                case '^':
                    isGameOver = eventSpike();
                    break;

                case 'B':
                    eventGetItem(playerPosition.copyIt());
                    gameBoard[playerPosition.posX][playerPosition.posY] = '.';
                    break;

                case '&':
                    isGameOver = eventBattle(playerPosition.copyIt(), false);
                    break;

                case 'M':
                    isGameOver = eventBattle(playerPosition.copyIt(), true);
                    if(!(isGameOver)){
                        return gameResult(false, "YOU WIN!");
                    }
                    break;
            }
            
            if(isGameOver){
                if(player.accessorySlot.check("RE")){
                    player.accessorySlot.deprecate("RE");
                    player.currentHp = player.maxHp;
                    playerPosition = startPosition.copyIt();
                    isGameOver = false;
                    //몹 회복은 배틀에서 처리
                }else{
                    return gameResult(true, String.format("YOU HAVE BEEN KILLED BY %s..", player.deadBy));
                }
            }

            // System.out.println(debugGame());
        }
        
        return gameResult(false, "Press any key to continue.");
    }

    private String gameResult(boolean playerDead, String endMessage){
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < gameBoard.length; i++){
            for(int j = 0; j< gameBoard[0].length; j++){
                if (i == playerPosition.posX && j == playerPosition.posY && !playerDead){
                    sb.append("@");
                }else{
                    sb.append(gameBoard[i][j]);  
                }
            }
            sb.append("\n");
        }

        sb.append("Passed Turns : ").append(commandIndex).append("\n");

        sb.append("LV : ").append(player.level).append("\n")
            .append(String.format("HP : %s/%s\n", player.currentHp, player.maxHp))
            .append(String.format("ATT : %s+%s\n", player.attackPoint, player.weaponSlot))
            .append(String.format("DEF : %s+%s\n", player.armorPoint, player.armorSlot))
            .append(String.format("EXP : %s/%s\n", player.expPoint, player.maxExp));

        sb.append(endMessage);
        return sb.toString();
    }

    private boolean eventSpike(){
        boolean playerDead = player.spikeDamaged();
        if(playerDead){
            player.deadBy = "SPIKE TRAP";
        }
        return playerDead;
    }

    private void eventGetItem(Position pos){
        Item newItem = items.get(pos);
        switch (newItem.itemm) {
            case "W":
                player.weaponSlot = Integer.parseInt(newItem.spec);
                break;
            case "A":
                player.armorSlot = Integer.parseInt(newItem.spec);
                break;
            case "O":
                player.accessorySlot.equipment(newItem.spec);
                break;
        }

    }
    
    private boolean eventBattle(Position pos, boolean isBoss){
        Monster enemy = monsters.get(pos);
        boolean firstAttack = false;
        boolean firstDamaged = false;

        if(player.accessorySlot.check("CO")){
            firstAttack = true;
        }

        if(isBoss){
            if (player.accessorySlot.check("HU")){
                player.healHp(999999);
                firstDamaged = true;
            }
        }

        //전투 시작
        boolean deathCheck = false;
        while(true){
            //플레이어 어택
            if (firstAttack){
                if(player.accessorySlot.check("DX")){
                    deathCheck = enemy.damaged(player.attack() * 3);
                }else{
                    deathCheck = enemy.damaged(player.attack() * 2);
                }
                firstAttack = false;
            }else{
                deathCheck = enemy.damaged(player.attack());
            }

            //플레이어 승리
            if(deathCheck){
                if(player.accessorySlot.check("HR")){
                    player.healHp(3);
                }
                //경험치 부스트 계산은 플레이어 객체에서
                gameBoard[playerPosition.posX][playerPosition.posY] = '.';
                player.getEXP(enemy.expPoint);
                return false;
            }

            // 적 어택
            if(firstDamaged){
                firstDamaged = false;
                continue;
            }
            deathCheck = player.damaged(enemy.attack());
            
            //플레이어 사망
            //부활 판정은 게임마스터에서
            if(deathCheck){
                enemy.healHp(999999);
                player.deadBy = enemy.monsterName;
                return true;
            }
        }
    }
}

class Item {
    String itemm;
    String spec;

    Item(String itemm, String spec){
        this.itemm = itemm;
        this.spec = spec;
    }
}

class Position implements Comparable<Position>{
    int posX;
    int posY;

    Position(int x, int y){
        posX = x;
        posY = y;    
    }

    public Position copyIt(){
        return new Position(posX, posY);
    }

    @Override
    public int compareTo(Position o) {
        if (posX == o.posX){
            return posY - o.posY;
        }
        return posX - o.posX;
    }

    @Override
    public int hashCode() {
        return Objects.hash(posX, posY);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Position other = (Position) obj;
        return posX == other.posX && posY == other.posY;
    }
}

class Creature{
    int maxHp;
    int currentHp;
    int attackPoint;
    int armorPoint;
    int expPoint;

    public Creature(int attack, int armor, int hp, int exp){
        maxHp = hp;
        attackPoint = attack;
        armorPoint = armor;
        expPoint = exp;
        currentHp = maxHp;
    }

    public int attack(){
        return attackPoint;
    }

    public boolean damaged(int damagePoint){
        int realDamagePoint = Integer.max(1, damagePoint - armorPoint);
        currentHp -= realDamagePoint;
        
        if (currentHp <= 0){
            return true;
        }
        return false;
    }

    public void healHp(int healPoint){
        currentHp += healPoint;
        if(currentHp >= maxHp){
            currentHp = maxHp;
        }
    }
}

class Monster extends Creature {
    String monsterName; 
    
    public Monster(String name, int attack, int armor, int hp, int exp){
        super(attack, armor, hp, exp);
        monsterName = name;
    }
}

class Player extends Creature{
    int maxExp;
    int level;
    int weaponSlot;
    int armorSlot;
    Accessories accessorySlot;
    String deadBy;

    public Player(){
        super(2, 2, 20, 0);
        level = 1;
        maxExp = level * 5;
        accessorySlot = new Accessories();
    }

    public boolean spikeDamaged(){
        if (accessorySlot.check("DX")){
            currentHp -= 1;
        }else{
            currentHp -= 5;
        }
        if (currentHp <= 0){
            currentHp = 0;
            return true;
        }
        return false;
    }

    @Override
    public int attack() {
        return attackPoint + weaponSlot;
    }
    
    @Override
    public boolean damaged(int damagePoint) {
        int realDamagePoint = Integer.max(1, damagePoint - (armorPoint + armorSlot));
        currentHp -= realDamagePoint;
        
        if (currentHp <= 0){
            currentHp = 0;
            return true;
        }
        return false;
    }

    public void getEXP(int exp){
        if (accessorySlot.check("EX")){
            exp = (int)(exp * 1.2);
        }
        
        expPoint += exp;
        if (expPoint >= maxExp){
            level++;
            maxHp += 5;
            attackPoint += 2;
            armorPoint += 2;
            currentHp = maxHp;
            maxExp = level * 5;
            expPoint = 0;
        }
    }
}

class Accessories {
    int slot = 0;
    HashMap<String, Boolean> acces = new HashMap<>();

    Accessories(){
        acces.put("HR", false);
        acces.put("RE", false);
        acces.put("CO", false);
        acces.put("EX", false);
        acces.put("DX", false);
        acces.put("HU", false);
        acces.put("CU", false);
    }

    void equipment(String target){
        if(! acces.containsKey(target)){
            System.out.println("장신구 존재하지 않음");
            return;
        }

        if (acces.get(target)){
            return;
        }

        if (slot >= 4){
            return;
        }

        acces.replace(target, true);
        slot++;
    }

    void deprecate(String target){
        if(! acces.containsKey(target)){
            System.out.println("장신구 존재하지 않음");
            return;
        }

        if (acces.get(target)){
            acces.replace(target, false);
            slot--;
        }
    }

    boolean check(String target){
        return acces.get(target);
    }
}