import React from "react";

export default function EmptyState(props) {
    return (
        <>
            <h3 style={styles.heading}>{props.title}</h3>
            <div className="list-group">
                <h4>Belum ada menu yang dipilih</h4>
                <h5>Klik salah satu item di daftar Menu/Produk</h5>
            </div>
        </>
    );
}

const styles = {
    heading: {
        fontFamily: "courier new"
    }
};