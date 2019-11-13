import React, { useState } from "react";
import List from "./components/List";
import EmptyState from "./components/EmptyState"
import dummyItems from "./items.json";
/**
 * Building React component using functional programming paradigm
 */
function App() {
// Btw, this is hooks. useState function returns an array
// contains the state and a function to set the state -> [state, setState].
// What you see below is array destruction.
// Let say you have an array const arr = ["aaa", "bbb"], to access the item
// we can use index arr[0] OR destruct it like below
// const [varName, index1] = arr, variable varName is guaranteed to get the value of index 0 OR "aaa"
// here is the illustration for this situation
// below is the return value of useState
// [favItems, setFavItems] = [state, setState]
    const [favItems, setFavItems] = useState(() => []);
    const [checked, setChecked] = useState(() => false);

    function handleItemClick(item, canDelete) {
// immutability
        const newItems = [...favItems];
        const newItem = { ...item };
// find index of item with id
        const targetInd = newItems.findIndex(it => it.id === newItem.id);
        if (targetInd < 0) newItems.push(newItem);
        else if (canDelete) newItems.splice(targetInd, 1); // delete 1 item at index targetInd
// schedule to set a new state
        setFavItems(newItems);
    }

    function handleChangeChecked() {
        setChecked(!checked);
    }
    
    function handleFavItemClick(item) {
        const newItems = [...favItems];
        const newItem = {...item};
        const targetInd = newItems.findIndex(it => it.id === newItem.id);
        if (targetInd < 0) newItems.push(newItem);
        else newItems.splice(targetInd, 1);
        setFavItems(newItems);
    }

    function showFavItem(checked) {
        if (checked === true && favItems.length > 0) {
            return (
                <div className="col-sm">
                    <List
                        title="My Favorite"
                        items={favItems}
                        onItemClick={(item) => handleItemClick(item, true)}
                        hide={false}
                    />
                </div>
            )
        } else if (checked === true && favItems.length == 0) {
            return (
                <div className="col-sm">
                    <EmptyState title="My Favorite" />
                </div>
            )
        }
    }

    return (
        <div className="container-fluid">
            <h1 className="text-center">
                Welcome!
                <small>Functional</small>
            </h1>
            <div className="row justify-content-center align-items center">
                <input type="checkbox" onChange={handleChangeChecked}/>
                <small>Show Favorite</small>
            </div>
            <div className="container pt-3">
                <div className="row">
                    <div className="col-sm">
                        <List
                            title="Our Menu"
                            items={dummyItems}
                            onItemClick={(item) => handleItemClick(item, false)}
                            hide={true}
                        />
                    </div>
                    {showFavItem(checked)}
                </div>
            </div>
        </div>
    );
}
export default App;
