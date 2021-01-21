var todoItems = JSON.parse(localStorage.getItem('todoItems'));

class TodoHeader extends React.Component {
    render() {
        return <div className="d-flex justify-content-center pt-3 mb-3"><h1>Todo list</h1></div>;
    }
}

class TodoForm extends React.Component {
    constructor(props) {
        super(props);
        this.onSubmit = this.onSubmit.bind(this);
    }
    componentDidMount() {
        this.refs.itemName.focus();
    }
    onSubmit(event) {
        event.preventDefault();
        var newItemValue = this.refs.itemName.value;
        if (newItemValue) {
            this.props.addItem({ newItemValue });
            this.refs.form.reset();
        }
    }
    render() {
        return (
            <form ref="form" onSubmit={this.onSubmit} className="input-group">
                <input type="text" ref="itemName" className="form-control" placeholder="Что необходимо сделать?" />
                <button type="submit" className="btn btn-success">Добавить</button>
            </form>
        );
    }
}

class TodoList extends React.Component {
    render() {
        var items = this.props.items.map((item, index) => {
            return (
                <TodoListItem key={index} item={item} index={index} removeItem={this.props.removeItem} markTodoDone={this.props.markTodoDone} />
            );
        });
        return (
            <ul className="list-group mt-2"> {items} </ul>
        );
    }
}

class TodoListItem extends React.Component {
    constructor(props) {
        super(props);
        this.onClickDone = this.onClickDone.bind(this);
    }
    onClickDone() {
        var index = parseInt(this.props.index);
        this.props.markTodoDone(index);
    }
    render() {
        var todoClass = this.props.item.done ?
            "text-success d-flex align-items-center" :
            "text-danger d-flex align-items-center";
        var iClass = this.props.item.done ?
            "fas fa-check i" : "fas fa-times i";
        const iStyle = {
            
        };
        return (
            <li className="list-group-item">
                <div className={todoClass}>
                    <i className={iClass} onClick={this.onClickDone}></i>
                    <div>
                        {this.props.item.value}
                    </div>
                </div>
            </li>
        );
    }
}

class TodoApp extends React.Component {
    constructor(props) {
        super(props);
        this.addItem = this.addItem.bind(this);
        this.markTodoDone = this.markTodoDone.bind(this);
        this.state = { todoItems: todoItems };
    }
    addItem(todoItem) {
        todoItems.unshift({
            index: todoItems.length + 1,
            value: todoItem.newItemValue,
            done: false
        });
        localStorage.setItem('todoItems', JSON.stringify(todoItems));
        this.setState({ todoItems: todoItems });
    }
    markTodoDone(itemIndex) {
        var todo = todoItems[itemIndex];
        todo.done = !todo.done;
        this.setState({ todoItems: todoItems });
        setTimeout(() => {
            todoItems.splice(itemIndex, 1);
            localStorage.setItem('todoItems', JSON.stringify(todoItems));
            this.setState({ todoItems: todoItems });
        }, 1000);
    }
    render() {
        return (
            <div id="main" className="w-50 m-auto">
                <TodoHeader />
                <TodoForm addItem={this.addItem} />
                <TodoList items={this.props.initItems} markTodoDone={this.markTodoDone} />
            </div>
        );
    }
}

ReactDOM.render(
    <TodoApp initItems={todoItems} />,
    document.getElementById('app')
);