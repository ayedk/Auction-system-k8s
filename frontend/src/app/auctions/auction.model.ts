export class Auction{
    public id:number;
    public imagePath:string;
    public title: string;
    public initialPrice: number;
    public description: string;
    public startDate: Date;
    public endDate: Date;
    constructor(id:number,path:string,title:string,init_pr:number,description:string,start_d:Date,end_d:Date){
        this.id = id;
        this.title = title;
        this.imagePath = path;
        this.initialPrice = init_pr;
        this.description = description;
        this.startDate = start_d;
        this.endDate = end_d;
    }
}