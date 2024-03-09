export class User {
    id: number | null = null;
    userName: string | null = null;
    email: string  | null = null;
    password: string  | null = null;
    created_at: string | null = null;
    updated_at: string | null  = null;

    constructor(user?: User) {
        if (user) {
            this.id = user.id;
            this.userName = user.userName;
            this.email = user.email;
            this.password = user.password;
            this.created_at = user.created_at;
            this.updated_at = user.updated_at;
        }
    }
}