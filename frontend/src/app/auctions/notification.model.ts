export class Notification{
    public content:String;
    public timestamp:Date;
    

    constructor(msg:String,tmp:Date){
        this.content = msg;
        this.timestamp = tmp;
    }
}