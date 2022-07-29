import { createContext, useCallback, useEffect, useState, useRef } from 'react';

const LeilaoProcessContext = createContext({});

export function ProviderLeilaoProcess({ children }) {
    const [userConnected, setUserConneted] = useState([])
    const [itensLeilao, setItemLeilao] = useState([])
    const [leilao, setLeilao] = useState([])
    const [eventos, setEventos] = useState([])
    const socketIORef = useRef()

    const handleSocketMessage = useCallback(message => {
        switch (message.msgType) {
            case 'LIST-ITEMS-AVAILABLES':
                setItemLeilao(message.data)
                break

            case 'LIST-USER-AVAILABLES':
                setUserConneted(message.data)
                break

            case 'LIST-LEILAO':
                console.log('LIST-LEILAO', message.data)
                setLeilao(message.data)
                break

            default:
                console.log('message not handling yet')
        }
    }, [userConnected])

    useEffect(() => {
        if (!socketIORef.current?.connected) {
            socketIORef.current = new WebSocket("ws://localhost:8081/leilao");
            socketIORef.current.connected = true

            socketIORef.current.onerror = () => {
                console.log('erro no socket')
                socketIORef.current.connected = false
                setUserConneted([])
            }
    
            socketIORef.current.onclose = () => {
                console.log('disconneted')
                socketIORef.current.connected = false
                setUserConneted([])
            }
    
            socketIORef.current.onopen = (_data) => {}
    
            socketIORef.current.onmessage = (data) => {
                handleSocketMessage(JSON.parse(data.data))
            }
        }
        

    }, [userConnected])

    return (
        <LeilaoProcessContext.Provider value={{ userConnected, itensLeilao, eventos }}>
            {children}
        </LeilaoProcessContext.Provider>
    )
}

export default LeilaoProcessContext