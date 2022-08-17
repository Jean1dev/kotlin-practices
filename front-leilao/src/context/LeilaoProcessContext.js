import { createContext, useCallback, useEffect, useState, useRef } from 'react';

const LeilaoProcessContext = createContext({});

export function ProviderLeilaoProcess({ children }) {
    const [userConnected, setUserConneted] = useState([])
    const [itensLeilao, setItemLeilao] = useState([])
    const [leilaoAtivo, setLeilao] = useState([])
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

            case 'LEILAO-CREATED':
                alert('Novo leilão criado')
                setLeilao([JSON.parse(message.data)])
                break

            case 'LEILAO-DATA':
                setLeilao(JSON.parse(message.data))
                break

            case 'OCORRENCIAS':
                console.log('OCORRENCIAS', message.data)
                setEventos(message.data)
                break

            default:
                console.log('message not handling yet', message)
        }
    }, [userConnected, leilaoAtivo])

    const join = useCallback((data) => {
        socketIORef.current.send(JSON.stringify({ type: 'join', data: { ...data, valorLance: 1 } }))
    }, [])

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

            socketIORef.current.onopen = (_data) => { }

            socketIORef.current.onmessage = (data) => {
                handleSocketMessage(JSON.parse(data.data))
            }
        }


    }, [userConnected])

    return (
        <LeilaoProcessContext.Provider value={{ userConnected, itensLeilao, eventos, leilaoAtivo, join }}>
            {children}
        </LeilaoProcessContext.Provider>
    )
}

export default LeilaoProcessContext