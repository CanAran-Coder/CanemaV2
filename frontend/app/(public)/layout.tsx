import Navbar from "@/components/Navbar"

function PublicLayout({ children }: { children: React.ReactNode }) {
    return (
        <div>
            <Navbar />
            {children}

        </div>
    )
}

export default PublicLayout