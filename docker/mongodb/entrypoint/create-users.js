let adminDb = connect("mongodb://localhost/admin");
adminDb.auth('root', 'admin');

let emailDb = adminDb.getSiblingDB('gerenciador-tarefas');

emailDb.createUser(
    {
        user: "feltex",
        pwd: "feltex1234",
        roles: [{role: "readWrite", db: "gerenciador-tarefas"}]
    }
);
